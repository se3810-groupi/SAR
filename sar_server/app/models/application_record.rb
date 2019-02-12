# frozen_string_literal: true

# Base class for all model classes.
class ApplicationRecord < ActiveRecord::Base
  self.abstract_class = true
end
