# README

## General Information

**Author:** Ryley Powell

**Course:** SE 3810

**Group:** Group I

**Project:** Social Augmented Reality Application

**Project Component:** Server

## Ruby and Rails Version

### Recommended Versions
Ruby 2.5.1

Rails 5.2.1

### Other Versions

You could attempt to run this using different versions of Ruby and Rails, but may result in a broken application and **is highly discouraged unless you are familiar with Ruby and, by association, Rails.**

If you do decide to run this using a different version of Ruby and/or Rails, refer to the [official rails guide](https://guides.rubyonrails.org/upgrading_ruby_on_rails.html) on upgrading.

You **will** need to make modification to version numbers for Ruby and Ruby Gems under *~/SAR-Server/sar_server/Gemfile*.

---
# Setup Instructions

    Notes: 
    1. The following sections should be done in order.
    2. Everything should be done from the same terminal.
    3. Some of the following steps are potentially skipable depending on your previous setup.
       It will be denoted with a **(S)** for the step.

## Setting-up Ruby

### Installing Ruby via RVM (Linux)

1. **If** you don't have RVM installed, see [this](https://rvm.io) page for gpg2 keys

    You may need to install gpg2, which the terminal will tell you if this is needed.

2. **Run** `\curl -sSL https://get.rvm.io | bash -s stable`

3. **Run** `rvm install 2.5.1`

4. (S) **Run** `/bin/bash --login`

5. (S) **Run** `rvm use 2.5.1`

### Installing Gem Set via Bundler

1. **Assuming** this is the first installation of ruby, run `gem install bundler` from the same terminal used above

2. **Run** `bundle install` from anywhere inside the *~/SAR-Server/sar_server/...* directory

    This will install all Ruby gems, **including Rails**, specified from *~/SAR-Server/sar_server/Gemfile* to wherever *Ruby* is located on your machine.
    
3. (S) **Depending** on previous setup with your machine, you might only need to run `bundle update` instead

Other information on the *bundler* gem can be found [here](https://bundler.io).

## Environment Configurations

    The application includes three different environments as listed below.
    It's unlikely you will need to use Development as this is only a skeletal system,
    but has been included for consistancy.
    
    Note: You will need to follow the Database creation and initalization for each
    configuration you would like to the run the application under, unless stated otherwise.

### Development

This is likely the default environment you will be set to upon first building the application and attempting to run it.

To enter development mode, run `export RAILS_ENV=development`
### Testing

To enter testing mode, run `export RAILS_ENV=test`

### Production

To enter production mode, run `export RAILS_ENV=production`

## Database Setup

    Note: I will be using the development environment as example for the following.

### Database Creation

    Note: This sub-section should only need to be done once per environment configuration.
    Barring exceptions like deleting the *.sqlite3 file.
    
1. **Ensure** you have run one of the commands from *Environment Configurations* (i.e. `export RAILS_ENV=development`)

2. **Check** under *~/SAR-Server/sar_server/db/...* for the *.sqlite3 file (i.e. *development.sqlite3*)

3. **If** the *.sqlite3 file **does not** already exist, then run `rake db:create`

    **If**, for whatever reason, the *.sqlite3 file already exists, then you can re-create it, if you so choose, via `rake db:reset`
    
    **However**, running `rake db:reset` is otherwise discouraged unless something is completely broken, a bad migration that broke your database, or other extenuating circumstance.
    
### Database Migration

    Note: This sub-section should only need to be done once per creation of an *.sqlite3 file, and on creation of a migration.

1. **Ensure** you have run one of the commands from *Environment Configurations* (i.e. `export RAILS_ENV=development`)

2. **Check** under *~/SAR-Server/sar_server/db/...* for the *.sqlite3 file (i.e. *development.sqlite3*)

3. **Run** `rake db:migrate`

    **Note:** Further runs of `rake db:migrate` will not break anything, but will not do anything if nothing has been changed.

### Database Initialization
    
    Notes: 
    1. This sub-section should only need to be run once per creation or migration.
    2. This is not needed for testing, as testing is a special case in terms of the database.
    
1. **Ensure** you have run one of the commands from *Environment Configurations* (i.e. `export RAILS_ENV=development`)

2. **Check** under *~/SAR-Server/sar_server/db/...* for the *.sqlite3 file (i.e. *development.sqlite3*)

3. **Ensure** you have run `rake db:migrate` at least once

4. **Run** `rake db:seed`

---
# Running the Application

    Notes:
    1. You can run the server in two modes, production and development. 
    2. For the most part, they will be the same, with some small differences in configuration.

1. **Choose** an environment from *Environment Configurations*

2. **Check** the steps from *Database Setup* have been done

3. **Run** `rails s` from the terminal

4. **Shut-down** via entering `CTRL+C` into the terminal

---
# Testing

1. **Ensure** that you are in the testing environment (i.e. `export RAILS_ENV=test`)

2. **Check** the steps from *Database Setup* have been done

3. **Run** `rake test`