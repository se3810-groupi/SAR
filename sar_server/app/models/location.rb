# frozen_string_literal: true

# Class representing a geolocation
# @!attribute latitude - a floating point number to 6 digits of precision measured in degrees
# @!attribute longitude - a floating point number to 6 digits of precision measured in degrees
# @!attribute altitude - a floating point number relative to the ground at a particular lat/lon pair
#                       and is measured in meters
class Location < ApplicationRecord
  has_many :tags
  include Math

  CACHING_DISTANCE = 0.1 # kilometers

  EARTH_KM = 6371 # kilometers

  def self.all_with_tags(origin, magnitude)
    within_distance = Location.all_within_radial_distance(origin, magnitude)
    within_distance.delete_if do |loc|
      loc.tags.empty?
    end
  end

  def self.all_within_radial_distance(origin, magnitude)
    magnitude = CACHING_DISTANCE if magnitude.nil?
    Location.all.to_a.keep_if do |loc|
      Location.distance_calculation(origin, loc) <= magnitude
    end
  end

  # Formula credited to Jan Philip Matuschek from:
  # janmatuschek.de/LatitudeLongitudeBoundingCoordinates
  def self.distance_calculation(location_one, location_two)
    # Convert to radians
    lat_one, lon_one = Location.location_degree_to_radians(location_one)
    lat_two, lon_two = Location.location_degree_to_radians(location_two)

    # Calculation
    Math.acos(Math.sin(lat_one) * Math.sin(lat_two) +
                        Math.cos(lat_one) * Math.cos(lat_two) *
                            Math.cos(lon_two - lon_one)) * EARTH_KM
  end

  # convert from degree to radians
  def self.location_degree_to_radians(location)
    lat = location.latitude * (Math::PI / 180)
    lon = location.longitude * (Math::PI / 180)
    [lat, lon]
  end
end
