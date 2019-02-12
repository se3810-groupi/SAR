class ApplicationController < ActionController::API
  include Response

  protected

  def find_by_lat_lon
    Location.where(latitude: params[:latitude], longitude: params[:longitude]).first_or_create do |loc|
      loc.latitude = params[:latitude]
      loc.longitude = params[:longitude]
      loc.altitude = params[:altitude] || 0
    end
  end

end
