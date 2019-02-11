# Supported Endpoints

    Notes:
    
    1. These are the currently supported endpoints for SAR-Server skeletal system.
    
    2. All other endpoints can be found running `rails routes` from inside the sar_server directory.
    
    3. While other endpoints may exist, there is no garunteee that they will work.

## GET /locations/show

#### Description:

This will return all locations that exist within a default 100 meter radial distance from an origin point.

#### Response:

Application/JSON
```json
[
     {
         "id": 1,
         "longitude": -87.908549,
         "latitude": 43.044151,
         "altitude": null
     },
     {
         "id": 2,
         "longitude": -87.908238,
         "latitude": 43.044153,
         "altitude": null
     },
     {
         "id": 4,
         "longitude": -87.908614,
         "latitude": 43.044979,
         "altitude": null
     },
     {
         "id": 9,
         "longitude": -87.908861,
         "latitude": 43.044379,
         "altitude": null
     },
     {
         "id": 11,
         "longitude": -87.908547,
         "latitude": 43.044692,
         "altitude": 0
     }
 ]
```

#### Required Parameters:

`latitude` - a floating point number in degrees that assumes at least 6 decimals (i.e. 0.123456)

`longitude` - a floating point number in degrees that assumes at least 6 decimals (i.e. 0.123456)

#### Optional Parameters:

`location_id` - an integer value that represents the location inside the database. This can be specified in place of a `latitude` and `longitude` pair if the ID is known

`proximity` - a distance in kilometers (Note: Anything other than a positive, integer value may have un-expected results)

## POST /tags

#### Description:

This will create a new tag within the database.

#### Response:

Application/JSON

```json
{
    "id": 13,
    "image": "Something",
    "label": "SomethingElse",
    "expired": null,
    "paid_tag": null,
    "reported": null,
    "location_id": 12,
    "users_id": 1
}
```

#### Required Parameters:

`latitude` - a floating point number in degrees that assumes at least 6 decimals (i.e. 0.123456)

`longitude` - a floating point number in degrees that assumes at least 6 decimals (i.e. 0.123456)

`user_id` - an integer value that represents a user account inside the database. **THIS IS CURRENTLY BEING SPOOFED SO IT IS NOT NECESSARY, BUT IF YOU WOULD LIKE TO SET IT ANYWAYS, FEEL FREE TO**

#### Optional Parameters:

`location_id` - an integer value that represents the location inside the database. This can be specified in place of a `latitude` and `longitude` pair if the ID is known

`image` - a string that is intended to mock an image

`label` - a string that describes the tag

`expired` - **CURRENTLY NOT-SUPPORTED**

`paid_tag` - **CURRENTLY NOT-SUPPORTED**

`reported` - **CURRENTLY NOT-SUPPORTED**

## GET /tags/near_me

#### Description:

This will return all tags that within a defaulted 100 meter radial distance from a specified origin point.

#### Response:

Application/JSON

```json
[
    {
        "id": 1,
        "image": "Loggins",
        "label": "MSOE Campus Center",
        "expired": null,
        "paid_tag": null,
        "reported": null,
        "location_id": 1,
        "users_id": 1
    },
    {
        "id": 2,
        "image": "Snuffles",
        "label": "MSOE School of Nursing",
        "expired": null,
        "paid_tag": null,
        "reported": null,
        "location_id": 1,
        "users_id": 1
    },
    {
        "id": 3,
        "image": "Baby Legs",
        "label": "German English Academy",
        "expired": null,
        "paid_tag": null,
        "reported": null,
        "location_id": 2,
        "users_id": 1
    },
    {
        "id": 5,
        "image": "Beth's Mytholog",
        "label": "Blatz Condominiums",
        "expired": null,
        "paid_tag": null,
        "reported": null,
        "location_id": 4,
        "users_id": 1
    },
    {
        "id": 10,
        "image": "Snowball",
        "label": "CC-03",
        "expired": null,
        "paid_tag": null,
        "reported": null,
        "location_id": 9,
        "users_id": 1
    }
]
```

#### Required Parameters:

`latitude` - a floating point number in degrees that assumes at least 6 decimals (i.e. 0.123456)

`longitude` - a floating point number in degrees that assumes at least 6 decimals (i.e. 0.123456)

#### Optional Parameters:

`location_id` - an integer value that represents the location inside the database. This can be specified in place of a `latitude` and `longitude` pair if the ID is known

`proximity` - a distance in kilometers (Note: Anything other than a positive, integer value may have un-expected results)

## /monitor/ping

#### Description:

A spoofed version of pinging the database that is rudimentary and returns a minimal message with an 'OK' status

#### Response:

```json
{
    "status": "ok",
    "message": "You've pinged the SAR Server"
}

```

#### Required Parameters:

N/A

#### Optional Parameters:

N/A