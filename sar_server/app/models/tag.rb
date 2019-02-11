# frozen_string_literal: true

# The displayed content of the SAR application
# @!attribute image - an image stored as raw text
# @!attribute label -
class Tag < ApplicationRecord
  belongs_to :location, foreign_key: :location_id
  belongs_to :user, foreign_key: :users_id

  def self.spoofed_create(params)
    params[:users_id] = User.first.id
    if params[:location_id].nil?
      params[:location_id] =
        Location.find_or_create_by(latitude: params[:latitude], longitude: params[:longitude]).id
    end
    Tag.create(image: params[:image],
               label: params[:label],
               users_id: params[:users_id],
               location_id: params[:location_id])
  end

  def self.near_me(origin, magnitude)
    locations_with_tags = Location.all_with_tags(origin, magnitude)
    tags = []
    locations_with_tags.each do |loc|
      loc.tags.each do |t|
        tags << t
      end
    end
    tags
  end
end
