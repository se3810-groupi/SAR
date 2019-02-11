# This file is auto-generated from the current state of the database. Instead
# of editing this file, please use the migrations feature of Active Record to
# incrementally modify your database, and then regenerate this schema definition.
#
# Note that this schema.rb definition is the authoritative source for your
# database schema. If you need to create the application database on another
# system, you should be using db:schema:load, not running all the migrations
# from scratch. The latter is a flawed and unsustainable approach (the more migrations
# you'll amass, the slower it'll run and the greater likelihood for issues).
#
# It's strongly recommended that you check this file into your version control system.

ActiveRecord::Schema.define(version: 2019_02_10_204139) do

  create_table "locations", force: :cascade do |t|
    t.float "longitude"
    t.float "latitude"
    t.float "altitude"
  end

  create_table "tags", force: :cascade do |t|
    t.text "image"
    t.string "label"
    t.datetime "expired"
    t.boolean "paid_tag"
    t.boolean "reported"
    t.integer "location_id"
    t.integer "users_id"
    t.index ["location_id"], name: "index_tags_on_location_id"
    t.index ["users_id"], name: "index_tags_on_users_id"
  end

  create_table "users", force: :cascade do |t|
    t.string "given_name"
    t.string "family_name"
    t.string "username"
    t.string "email"
    t.string "password"
    t.text "friends_list"
    t.boolean "is_admin"
    t.boolean "account_locked"
  end

end
