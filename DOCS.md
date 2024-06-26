# DOCUMENTAZIONE API ROUTE

/auth/v1/signup <br>
**richiesta:**<br>
{
"username": "username1",
"email":"username@gmail.com",
"password":"password1",
"firstName":"name1",
"lastName":"surname1",
"birthday":"2011-02-17"
}<br>
**risposta:**<br>
{
"message": "User registered successfully!"
}

/auth/v1/signin<br>
**richiesta:**<br>
{
"username": "username1",
"password":"password1"
}<br>
**risposta:**<br>
{
"token": "<token>",
"type": "Bearer",
"id": <id>,
"username": "username2",
"email": "username2@gmail.com",
"firstName": "name2",
"lastName": "surname2",
"birthday": "2012-02-27",
"avatarUrl": null
}

# Post CRUD

_C_<br>
POST
/v1/newPost <br>
**richiesta:** <br>
{
"title": "post title",
"description":"description",
"imageUrl":null,
"type":"post",
"profile":2
}<br>
**risposta:**<br>
{
"id": <id>,
"title": "post title",
"description": "description",
"imageUrl": null,
"createdAt": [2024,3,14,16,50,8,743859100], //[yyyy,mm,dd,HH,mm,ss,cc]
"likes": 0,
"type": "post",
"profile": 2
}

_R_ <br>
GET
/v1/getPost/{postid} <br>
**richiesta:** <br>
_token_

**risposta:**
{
"id": _id_,
"title": "post title",
"description": "description",
"imageUrl": null,
"createdAt": [2024,3,14,16,50,8,743859100],
"likes": 0,
"type": "post",
"profile": 2
}

/v1/getPosts/{profilename} <br>
**richiesta:** <br>
_token_

**risposta:**<br>
{
"content": [
{
"id": 7,
"title": "post title",
"description": "description",
"imageUrl": null,
"createdAt": [2024,3,14,16,50,8,743859000],
"type": "post",
"profile": 2,
"likes": 0
}
],
"pageable": {
"pageNumber": 0,
"pageSize": 20,
"sort": {
"empty": true,
"sorted": false,
"unsorted": true
},
"offset": 0,
"paged": true,
"unpaged": false
},
"last": true,
"totalPages": 1,
"totalElements": 1,
"size": 20,
"number": 0,
"sort": {
"empty": true,
"sorted": false,
"unsorted": true
},
"numberOfElements": 1,
"first": true,
"empty": false
}

/v1/updatePost/{postid} <br>
**richiesta:** <br>
_token_
{
"title": "post modificato",
"description":"description2",
"type":"post"
}

**risposta:**<br>
{
"id": _id_,
"title": "post modificato",
"description": "description2",
"imageUrl": null,
"createdAt": [2024,3,14,16,50,8,743859000],
"likes": 0,
"type": "post",
"profile": 2
}

_D_ <br>
/v1/deletePost/{id} <br>
**richiesta:** <br>
_token_

**risposta:**
Post deleted with id: 11

# Comment CRUD

_C_ <br>
/v1/newComment <br>
**richiesta:** <br>
_token_
{
"text": "questo è un commento",
"post":7,
"profile":2
}

**risposta:** <br>
{
"id": 9,
"text": "questo è un commento",
"createdAt": [2024,3,18,15,17,46,451177700],
"likes": 0,
"profile": 2,
"post": 7
}

_R_ <br>
/v1/getComment/{CommentId}<br>
**richiesta:** <br>
_token_
**risposta:** <br>
{
"id":8,
"text":"questo è un commento",
"createdAt":[2024,3,15,17,38,6,408971000],
"likes":0
}

/v1/getComments/{postid}<br>
**richiesta:** <br>
_token_
**risposta:** <br>
{
"content": [
{
"id": 8,
"text": "questo è un commento",
"createdAt": [2024,3,15,17,38,6,408971000],
"likes": 0,
"profile": 2,
"post": 7
},
{
"id": 9,
"text": "questo è un commento modificato ",
"createdAt": [2024,3,18,15,17,46,451178000],
"likes": 0,
"profile": 1,
"post": 7
}
],
"pageable": {
"pageNumber": 0,
"pageSize": 20,
"sort": {
"empty": true,
"sorted": false,
"unsorted": true
},
"offset": 0,
"unpaged": false,
"paged": true
},
"last": true,
"totalElements": 2,
"totalPages": 1,
"size": 20,
"number": 0,
"sort": {
"empty": true,
"sorted": false,
"unsorted": true
},
"first": true,
"numberOfElements": 2,
"empty": false
}

_U_ <br>
/v1/updateComment/{CommentId}<br>
**richiesta:** <br>
_token_

**risposta**: <br>
Comment updated with id: _id_
{
"id":_id_,
"text":"questo è un commento modificato ",
"createdAt":[2024,3,18,15,17,46,451178000],
"likes":0
}

_U_ <br>
/v1/deleteComment/{CommentId}<br>
**richiesta:** <br>
_token_

**risposta**: <br>
Comment deleted with id: _id_

# Follow CRUD

_C_
/v1/{followerProfilename}/follow/{followingProfilename}
**richiesta:** <br>
_token_<br>

**risposta**: <br>
{
"followed":"username1",
"follower":"username2",
"createdAt":[2024,3,18,16,40,50,910917600]
}

_R_
/v1/getFollows/{Profileid}
**richiesta:** <br>
_token_<br>
**risposta**: <br>
[
{
"id": 2,
"profilename": "username2",
"firstName": "name2",
"lastName": "surname2",
"birthday": "2012-02-27",
"bio": null,
"avatarUrl": null
}
]

/v1/getFollowers/{Profileid}
**richiesta:** <br>
_token_<br>
**risposta**: <br>
[
{
"id": 1,
"profilename": "username1",
"firstName": "name1",
"lastName": "surname1",
"birthday": "2011-02-17",
"bio": null,
"avatarUrl": null
}
]
_D_
/v1/deleteFollow/{Profilename}/{Followingname}
**richiesta:** <br>
_token_<br>
**risposta**: <br>
Follow deleted

# Like

/v1/likePost/{postId}
**richiesta:** <br>
_token_<br>
**risposta**: <br>
Success

/v1/likeComent/{commentId}
**richiesta:** <br>
_token_<br>
**risposta**: <br>
Success

# Profile CRUD

_C_
/v1/newProfile
**richiesta:** <br>
_token_<br>
{
"user":"3",
"profilename":"user3profile7",
"firstName":"user3",
"lastName":"profile7",
"birthday":"2024-03-15",
"bio":"bio profilo secondario",
"avatarUrl":null
}

**risposta**: <br>
{
"id": 3,
"profilename": "user1profile3",
"firstName": "user1",
"lastName": "profile3",
"birthday": [2023,3,23],
"bio": "bio profilo secondario",
"avatarUrl": null,
"comments": 0,
"followers": 0,
"following": 0
}

_R_
/v1/getProfiles/{userId}

**richiesta:**<br>
_token_
**risposta:** <br>
{
"content": [
{
"id": 5,
"profilename": "user3profile2",
"firstName": "user3",
"lastName": "profile1",
"birthday": "2024-03-20",
"bio": "bio profilo secondario",
"avatarUrl": null
},
{
"id": 7,
"profilename": "user3profile3",
"firstName": "user3",
"lastName": "profile1",
"birthday": "2024-03-20",
"bio": "bio profilo secondario",
"avatarUrl": null
},
{
"id": 8,
"profilename": "user3profile4",
"firstName": "user3",
"lastName": "profile1",
"birthday": "2024-03-20",
"bio": "bio profilo secondario",
"avatarUrl": null
},
{
"id": 10,
"profilename": "user3profile5",
"firstName": "user3",
"lastName": "profile5",
"birthday": "2024-03-15",
"bio": "bio profilo secondario",
"avatarUrl": null
},
{
"id": 11,
"profilename": "user3profile6",
"firstName": "user3",
"lastName": "profile6",
"birthday": "2024-03-15",
"bio": "bio profilo secondario",
"avatarUrl": null
},
{
"id": 12,
"profilename": "user3profile7",
"firstName": "user3",
"lastName": "profile7",
"birthday": "2024-03-15",
"bio": "bio profilo secondario",
"avatarUrl": null
}
],
"pageable": "INSTANCE",
"totalElements": 6,
"totalPages": 1,
"last": true,
"size": 6,
"number": 0,
"sort": {
"sorted": false,
"unsorted": true,
"empty": true
},
"numberOfElements": 6,
"first": true,
"empty": false
}

/v1/getProfile/{ProfileName}

**richiesta:**<br>
_token_
**risposta:** <br>
{
"id": 10,
"profilename": "user3profile5",
"firstName": "user3",
"lastName": "profile5",
"birthday": "2024-03-15",
"bio": "bio profilo secondario",
"avatarUrl": null
}

_U_
v1/updateProfile/{profileId}

**richiesta:**<br>
_token_
{
"user":"1",
"profilename":"Profilo1",
"firstName":"modifica",
"lastName":"modifica",
"birthday":"2024-03-15",
"bio":"bio profilo modificata",
"avatarUrl":null
}
**risposta:** <br>
{
"id": 1,
"profilename": "Profilo11",
"firstName": "modifica",
"lastName": "modifica",
"birthday": [2024,3,15],
"bio": "bio profilo modificata",
"avatarUrl": null,
"comments": 0,
"followers": 0,
"following": 0
}

_D_
/v1/deleteProfile/{profileId}

**richiesta:** <br>
_token_
**risposta:** <br>
Profile deleted with id: 3

# User CRUD

_R_ <br>
/v1/getUser/{id}

**richiesta:** <br>
_token_
**risposta:** <br>
{
"id": 1,
"username": "username1",
"email": "username1@gmail.com",
"createdAt": [2024,3,20,17,17,1,720473000],
"updatedAt": [2024,3,20,17,17,1,720473000],
"lastLoginAt": [2024,3,21,16,49,37,527576000],
"lastOnlineAt": [2024,3,21,16,49,37,528541000]
}

_U_ <br>
/v1/updateUser/{id}

**richiesta:** <br>
_token_
{
"username": "username1",
"email":"username1@gmail.com"
}

**risposta:** <br>
{
"id": 1,
"username": "username123",
"email": "username1@gmail.com",
"createdAt": [2024,3,20,17,17,1,720473000],
"updatedAt": [2024,3,21,17,32,56,291580400],
"lastLoginAt": [2024,3,21,17,25,8,572203000],
"lastOnlineAt": [2024,3,21,17,25,8,572203000]
}

_D_
v1/deleteUser/1
**richiesta:** <br>
_token_
{
"username": "username1",
"email":"username1@gmail.com"
}

**risposta:** <br>
User deleted with id: 1
