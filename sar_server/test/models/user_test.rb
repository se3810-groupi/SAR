# frozen_string_literal: true

require_relative '../test_helper'

class UserTest < ActiveSupport::TestCase
  test 'dump out user data' do
    user = users(:Ryley_Powell)
    expected = { given_name: 'Ryley',
                 family_name: 'Powell',
                 username: 'powellr',
                 email: 'powellr@msoe.edu',
                 password: 'something-something-something-darkside',
                 is_admin: true,
                 account_locked: false }

    assert_equal expected[:given_name], user.given_name
    assert_equal expected[:family_name], user.family_name
    assert_equal expected[:username], user.username
    assert_equal expected[:email], user.email
    assert_equal expected[:password], user.password
    assert_equal expected[:is_admin], user.is_admin
    assert_equal expected[:account_locked], user.account_locked
  end
end
