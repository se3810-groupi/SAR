# frozen_string_literal: true

require_relative '../test_helper'

class LocationTest < ActiveSupport::TestCase
  test 'make sure convert to radians is within 1e-6 of expected' do
    delta = 0.000001
    expected_lat = 0.751262158674 # radians
    expected_lon = -1.5342936207 # radians

    result_lat, result_lon = Location.location_degree_to_radians(locations(:CC_MSOE)) # radians

    assert_in_delta expected_lat, result_lat, delta
    assert_in_delta expected_lon, result_lon, delta
  end

  test 'ensure proper distance calculation within 1e-6 of expected' do
    delta = 0.000001

    # ORDER MATTERS HERE; ALL IN KILOMETERS
    expected_vals = [0.025273124, # CC -> German English Academy
                     0.063071485, # CC -> Grohmann Museum
                     0.09222083, # CC -> Blatz
                     0.207906785, # CC - Bar Louie
                     0.169740331, # CC -> Red Arrow Park
                     0.163984535, # CC -> RWH
                     0.216035069, # CC -> MLH
                     0.03585514] # CC -> CC-03

    origin_point = locations(:CC_MSOE)

    # ORDER MATTERS HERE
    location_twos = [locations(:German_English_Academy),
                     locations(:Grohmann_Museum),
                     locations(:Blatz_Condos),
                     locations(:Bar_Louie),
                     locations(:Red_Arrow_Park),
                     locations(:RWH),
                     locations(:MLH),
                     locations(:CC_03)]

    result_vals = []

    location_twos.each do |loc2|
      result_vals << Location.distance_calculation(origin_point, loc2)
    end

    (0...expected_vals.length).each do |i|
      assert_in_delta expected_vals[i], result_vals[i], delta
    end
  end

  test 'all locations within a specified radial distance of an origin points is returned' do
    # DO FOR 100 METERS
    proximity = 0.1 # kilometers
    origin_point = locations(:CC_MSOE)
    expected_locations = %i[German_English_Academy Grohmann_Museum Blatz_Condos CC_03]
    not_included = %i[Bar_Louie Red_Arrow_Park RWH MLH]

    result_locations = Location.all_within_radial_distance(origin_point, proximity)

    expected_locations.each do |exp|
      assert_includes result_locations, locations(exp)
    end

    not_included.each do |unexp|
      assert_not_includes result_locations, locations(unexp)
    end

    # DO FOR 50 METERS
    proximity = 0.05 # kilometers
    # KEEP origin_point
    expected_locations = %i[German_English_Academy CC_03]
    not_included = %i[Grohmann_Museum Blatz_Condos Bar_Louie Red_Arrow_Park RWH MLH]

    result_locations = Location.all_within_radial_distance(origin_point, proximity)

    expected_locations.each do |exp|
      assert_includes result_locations, locations(exp)
    end

    not_included.each do |unexp|
      assert_not_includes result_locations, locations(unexp)
    end
  end

  # This test starts getting slowing things down due to the complex 'distance_calculations' used
  test 'all locations with tags within a specified radial distance of an origin point is returned' do
    # UPPER AND LOWER BOUNDS WITH RESPECT TO CC_MSOE WITHIN ABOUT 110 METERS
    lower_lat = 43.043
    upper_lat = 43.045
    lower_lon = -87.907
    upper_lon = -87.909

    # MAKE A BUNCH OF BOGUS POINTS WITH NO TAG ATTACHED
    1000.times do
      Location.find_or_create_by(latitude: Faker::Number.between(lower_lat, upper_lat),
                                 longitude: Faker::Number.between(lower_lon, upper_lon))
    end

    proximity = 0.1 # kilometers
    origin_point = locations(:CC_MSOE)
    expected_locations = %i[CC_MSOE German_English_Academy Grohmann_Museum Blatz_Condos CC_03]
    not_included = %i[Bar_Louie Red_Arrow_Park RWH MLH]

    result_locations = Location.all_with_tags(origin_point, proximity)

    # check that all expected locations have been included
    expected_locations.each do |exp|
      assert_includes result_locations, locations(exp)
    end

    # check that all locations not included are not in results
    not_included.each do |unexp|
      assert_not_includes result_locations, locations(unexp)
    end

    # Should be 8 based on expected_locations
    assert result_locations.length == 5
  end
end
