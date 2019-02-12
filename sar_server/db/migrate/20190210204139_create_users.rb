class CreateUsers < ActiveRecord::Migration[5.2]
  def change
    create_table :users do |t|
      t.string :given_name
      t.string :family_name
      t.string :username
      t.string :email
      t.string :password
      t.text :friends_list
      t.boolean :is_admin
      t.boolean :account_locked
    end

    change_table :tags do |t|
      t.references :users, foreign_key: true
    end
  end
end
