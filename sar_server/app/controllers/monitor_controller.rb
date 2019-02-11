# frozen_string_literal: true

# Handler for spoofing connection persistence as a fall-back plan in the event I'm unable to
# get ActiveJob or ActionCable working in time.
class MonitorController < ApplicationController

  ActionController::Parameters.permit_all_parameters = true

  def echo
    echo_hash = { status: :ok, message: 'You\'ve pinged the SAR Server' }
    params.each_pair do |k, v|
      echo_hash[k] = v
    end
    json_response(echo_hash)
  end

end
