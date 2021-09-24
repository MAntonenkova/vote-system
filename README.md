
Vote System project - voting system to decide where to have lunch
--------------------------------------------------------------------

REST API using Hibernate/Spring/SpringMVC (or Spring-Boot) without frontend

- 2 types of users: admin and regular users
- Admin can input a restaurant and its lunch menu of the day (2-5 items usually, just a dish name and price)
- Menu changes each day (admins do the updates)
- Users can vote on which restaurant they want to have lunch at
- Only one vote counted per user
- If user votes again the same day:
    1. If it is before 11:00 we asume that he changed his mind.
    2. If it is after 11:00 then it is too late, vote can't be changed
- Each restaurant provides new menu each day    


### Curl commands:
   #### Restaurants:
1.   curl -u user@gmail.com:password 'http://localhost:8080/votesystem/restaurants'

 - get all restaurants with their dishes

2. curl  -u user@gmail.com:password 'http://localhost:8080/votesystem/restaurants/8'

- get restaurant by id with its dish

3.  curl  -u admin@gmail.com:admin 'http://localhost:8080/votesystem/restaurant/add?name=Björn'

- add new restaurant

4. curl -XDELETE -u admin@gmail.com:admin 'http://localhost:8080/votesystem/restaurants/delete/9'

- delete a restaurant with its dish

5.  curl -XPUT -u admin@gmail.com:admin 'localhost:8080/votesystem/restaurants/edit/3?name=Ugolek'

- edit restaurant by its id

6.  curl -XPOST -u admin@gmail.com:admin -H "Content-type: application/json" -d '{
    "dishName":"Cottage cheese ring",
    "dishPrice": "390"
    }' 'localhost:8080/votesystem/restaurants/8/addDish'

- add new dish to a restaurant

7.  curl -XPUT -u admin@gmail.com:admin  -H "Content-type: application/json" -d '{
    "dishName":"Bonbonss",
    "dishPrice": "305"
    }' 'localhost:8080/votesystem/restaurants/9/dish/12/edit'

- edit dish for a restaurant and return restaurant with its dish

8.  curl -XDELETE -u admin@gmail.com:admin 'localhost:8080/votesystem/restaurants/dish/12/delete'

- delete dish


  #### Users:

1.  curl -u admin@gmail.com:admin 'http://localhost:8080/votesystem/users'

- get all users

2.  curl -u admin@gmail.com:admin 'http://localhost:8080/votesystem/users/3'

- get user by its id

3.  curl -XPUT -u admin@gmail.com:admin -H "Content-type: application/json" -d '{
    "name":"Kirill",
    "password": "password",
    "email": "kirill@gmail.com",
    "role": "USER",
    "enable": "true"
    }' 'http://localhost:8080/votesystem/users/add'

- add new user

4.  curl -XPUT -u rudolf@gmail.com:password -H "Content-type: application/json" -d '{
    "name":"Rodion",
    "password": "password",
    "email": "rudolf@gmail.com",
    "role": "USER",
    "enable": "true"
    }' 'http://localhost:8080/votesystem/users/edit/5'

- edit user

5.  curl -XDELETE -u alice@gmail.com:password 'http://localhost:8080/votesystem/users/delete/3'

- delete user

  #### Votes:

1.  curl -u admin@gmail.com:admin  'http://localhost:8080/votesystem/votes'

- get all votes for today

2.  curl -XPOST -u admin@gmail.com:admin  -H "Content-type: application/json" -d '{
    "voteDate" : "13.09.2021"
    }' 'http://localhost:8080/votesystem/votes'

- get all votes for the a certain day

3.  curl -u user@gmail.com:password  'http://localhost:8080/votesystem/vote?restId=1'

- voting