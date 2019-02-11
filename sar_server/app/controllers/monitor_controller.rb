# frozen_string_literal: true

# Handler for spoofing connection persistence as a fall-back plan in the event I'm unable to
# get ActiveJob or ActionCable working in time.
class MonitorController < ApplicationController

  def echo
    echo_hash = { status: :ok, message: 'You\'ve pinged the SAR Server' }
    json_response(echo_hash)
  end

end
