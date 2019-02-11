# frozen_string_literal: true

Rails.application.routes.draw do
  # For details on the DSL available within this file, see http://guides.rubyonrails.org/routing.html
  match '/tags/near_me', to: 'tags#near_me', as: 'near_me', via: :get

  resources :locations
  resources :tags
  resources :users


end
