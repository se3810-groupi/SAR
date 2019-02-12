# frozen_string_literal: true

# User account class that holds data about the user account
# @!attribute given_name - the "first" name of the user
# @!attribute family_name -  the "last" or surname of the user
# @!attribute username - the name of this user account
# @!attribute email - the email attached to this user account
# @!attribute password - the password attached to this user account
# @!attribute friends_list - a list of friend user accounts
# @!attribute is_admin - gives the user account admin privileges
# @!attribute account_locked - tells the application whether the user is blocked from using the application
class User < ApplicationRecord
  has_many :tags
end
