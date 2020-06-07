# planets-api
----
API to manage Star Wars planets data

## Routes
### Show All Planets
Returns data about all planets.

* **URL:** /planets/

* **Method:** *GET*

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** 
    ```
    [
      {
        "id": "5ed835bfd103a05e3acd9ac9",
        "name": "Saleucami",
        "climate": "hot",
        "terrain": "caves, desert, mountains, volcanoes",
        "filmsAppearancesCount": 0
      },
      {
        "id": "5ed992769ace5b6a2a495113",
        "name": "Kamino",
        "climate": "temperate",
        "terrain": "ocean",
        "filmsAppearancesCount": 1
      }
    ]
    ```

        OR
    ```
    []
    ```
    
### Search Planets
Returns data about all planets that the name match the searched string.

* **URL:** /planets?name={name}

* **Method:** *GET*

* **Query Params**: name=[string]

* **Example:** /planets?name=al

* **Success Response:**

  * **Code:** 200 <br />

      **Content:** 
      ```
      [
        {
          "id": "5ed828df95f7317b2b27ae93",
          "name": "Alderaan",
          "climate": "temperate",
          "terrain": "grasslands, mountains",
          "filmsAppearancesCount": 2
        },
        {
          "id": "5ed835bfd103a05e3acd9ac9"
          "name": "Saleucami",
          "climate": "hot",
          "terrain": "caves, desert, mountains, volcanoes",
          "filmsAppearancesCount": 1
        }
      ]
      ```
* **Error Response:**
  * **Code:** 404 NOT FOUND <br />
 
### Show Planet
Returns data about a single planet.

* **URL:** /planets/:id

* **Method:** *GET*
  
*  **URL Params:** `id=[integer]`

*  **Example:** /planets/5ed835bfd103a05e3acd9ac9

* **Success Response:**

  * **Code:** 200 <br />
  
      **Content:** 
      ```
        {
          "id": "5ed835bfd103a05e3acd9ac9",
          "name": "Tatooine",
          "climate": "arid",
          "terrain": "desert",
          "filmsAppearancesCount": 5
        }
      ```
* **Error Response:**
  * **Code:** 404 NOT FOUND <br />
  
### Save Planet
Stores data about a planet. 

It makes a request to https://swapi.dev/ with the planet name to retrieve the count of movie appearances.

* **URL:** /planets/:id

* **Method:** *POST*
  
*  **DATA Params**:
  ```
    {
      "name": "Alderaan",
      "climate": "temperate",
      "terrain": "grasslands, mountains"
    }
  ```

* **Success Response:**

  * **Code:** 201 <br />
  
      **Content:** 
      ```
        {
          "id": "5edd697ab16ec10b64f3fea1",
          "name": "Alderaan",
          "climate": "temperate",
          "terrain": "grasslands, mountains",
          "filmsAppearancesCount": 2
        }
      ```
* **Error Response:**
  * **Code:** 400 BAD REQUEST <br />
  
### Delete Planet
Removes data about a planet.

* **URL:** /planets/:id

* **Method:** *DELETE*
  
* **URL Params:** `id=[integer]`

* **Example:** /planets/5edd697ab16ec10b64f3fea1
* **Success Response:**

  * **Code:** 204 <br />
  
* **Error Response:**
  * **Code:** 404 NOT FOUND <br />
  
