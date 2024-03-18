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
"createdAt": [2024,3,14,16,50,8,743859100], //[yyyy,mm,dd,HH,mm,ss ,cc]
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
"text": "questo è un comm ",
"createdAt": [2024,3,18,15,17,46,451177700],
"likes": 0,
"profile": 1,
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
