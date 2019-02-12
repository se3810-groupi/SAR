# frozen_string_literal: true

# Module credited to Austin Kabiru (@akabiru_) from
# https://scotch.io/tutorials/build-a-restful-json-api-with-rails-5-part-one
module Response
  def json_response(object, status = :ok)
    render json: object, status: status, message: 'Connection OK'
  end
end
