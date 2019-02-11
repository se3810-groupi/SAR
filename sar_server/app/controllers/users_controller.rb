# frozen_string_literal: true

class UsersController < ApplicationController
  before_action :set_user, only: %i[show update destroy]

  def create; end

  def show; end

  def update; end

  def destroy; end

  private

  def set_user; end

  def user_params; end
end
