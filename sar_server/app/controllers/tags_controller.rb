# frozen_string_literal: true

class TagsController < ApplicationController
  before_action :set_tag, only: %i[show update destroy], except: :near_me

  def create
    newly_created = Tag.spoofed_create(params)
    json_response(newly_created)
  end

  def near_me
    @origin = if params[:location_id].nil?
                find_by_lat_lon
              else
                Location.find(params[:location_id])
              end
    @near_me = Tag.near_me(@origin, params[:proximity])
    json_response(@near_me)
  end

  private

  def set_tag
    @tag = Tag.find(params[:tag_id])
  end

  def tag_params
    params.permit(:image,
                  :label,
                  :users_id,
                  :location_id,
                  :tag_id,
                  :latitude,
                  :longitude,
                  :proximity)
  end
end
