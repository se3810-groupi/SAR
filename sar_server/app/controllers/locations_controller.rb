# frozen_string_literal: true

# Controller class for getting locations, updating locations with tags
class LocationsController < ApplicationController
  before_action :set_location, only: %i[show]

  def show
    @locations = Location.all_within_radial_distance(@location, params[:proximity])
    json_response(@locations)
  end

  private

  def set_location
    @location = if params[:location_id].nil?
                  find_by_lat_lon
                else
                  Location.find(params[:location_id])
                end
  end

  def location_params
    params.permit(:longitude,
                  :latitude,
                  :altitude,
                  :proximity,
                  :location_id,
                  :tag_id)
  end
end
