class CreateTags < ActiveRecord::Migration[5.2]
  def change
    create_table :tags do |t|
      t.text :image
      t.string :label
      t.timestamp :expired
      t.boolean :paid_tag
      t.boolean :reported
      t.references :location, foreign_key: true
    end
  end
end
