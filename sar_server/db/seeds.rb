# frozen_string_literal: true

# This file should contain all the record creation needed to seed the database with its default values.
# The data can then be loaded with the rails db:seed command (or created alongside the database with db:setup).
#
# Examples:
#
#   movies = Movie.create([{ name: 'Star Wars' }, { name: 'Lord of the Rings' }])
#   Character.create(name: 'Luke', movie: movies.first)

# Boundaries for Faker Data

spoofed_user = User.find_or_create_by(given_name: 'Ryley',
                                      family_name: 'Powell',
                                      username: 'powellr',
                                      email: 'powellr@msoe.edu',
                                      password: 'something-something-something-darkside',
                                      is_admin: true,
                                      account_locked: false)

cc_msoe = Location.find_or_create_by(latitude: 43.044151, longitude: -87.908549)
german_english_ac = Location.find_or_create_by(latitude: 43.044153, longitude: -87.908238)
grohmann_museum = Location.find_or_create_by(latitude: 43.043644, longitude: -87.908201)
blatz = Location.find_or_create_by(latitude: 43.044979, longitude: -87.908614)
bar_louie = Location.find_or_create_by(latitude: 43.045093, longitude: -87.910759)
red_arrow_park = Location.find_or_create_by(latitude: 43.043104, longitude: -87.910069)
rwh = Location.find_or_create_by(latitude: 43.045351, longitude: -87.907376)
mlh = Location.find_or_create_by(latitude: 43.045982, longitude: -87.907660)
cc_03 = Location.find_or_create_by(latitude: 43.044379, longitude: -87.908861)

Tag.find_or_create_by(image: Faker::Artist.name, label: 'MSOE Campus Center', location_id: cc_msoe.id, users_id: spoofed_user.id)
Tag.find_or_create_by(image: Faker::Artist.name, label: 'MSOE School of Nursing', location_id: cc_msoe.id, users_id: spoofed_user.id)
Tag.find_or_create_by(image: Faker::Artist.name, label: 'German English Academy', location_id: german_english_ac.id, users_id: spoofed_user.id)
Tag.find_or_create_by(image: Faker::Artist.name, label: 'Grohmann Museum', location_id: grohmann_museum.id, users_id: spoofed_user.id)
Tag.find_or_create_by(image: Faker::Artist.name, label: 'Blatz Condominiums', location_id: blatz.id, users_id: spoofed_user.id)
Tag.find_or_create_by(image: Faker::Artist.name, label: 'Bar Louie', location_id: bar_louie.id, users_id: spoofed_user.id)
Tag.find_or_create_by(image: Faker::Artist.name, label: 'Red Arrow Park', location_id: red_arrow_park.id, users_id: spoofed_user.id)
Tag.find_or_create_by(image: Faker::Artist.name, label: 'Roy W. Johnson Residence Hall', location_id: rwh.id, users_id: spoofed_user.id)
Tag.find_or_create_by(image: Faker::Artist.name, label: 'Margaret Loock Residence Hall', location_id: mlh.id, users_id: spoofed_user.id)
Tag.find_or_create_by(image: Faker::Artist.name, label: 'CC-03', location_id: cc_03.id, users_id: spoofed_user.id)
