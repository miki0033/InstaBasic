**DOCUMENTAZIONE**

/auth/v1/signup
**richiesta:**
{
"username": "username1",
"email":"username@gmail.com",
"password":"password1",
"firstName":"name1",
"lastName":"surname1",
"birthday":"2011-02-17"
}
**risposta:**
{
"message": "User registered successfully!"
}

/auth/v1/signin
**richiesta:**
{
"username": "username1",
"password":"password1"
}
**risposta:**
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

_C_
POST
/v1/newPost
**richiesta:**
{
"title": "post title",
"description":"description",
"imageUrl":null,
"type":"post",
"profile":2
}
**risposta:**
{
"id": <id>,
"title": "post title",
"description": "description",
"imageUrl": null,
"createdAt": [2024,3,14,16,50,8,743859100],
"likes": 0,
"type": "post",
"profile": 2
}
_R_
GET
/v1/getPost/{postid}
**richiesta:**
<token>

**risposta:**
{
"id": <id>,
"title": "post title",
"description": "description",
"imageUrl": null,
"createdAt": [2024,3,14,16,50,8,743859100],
"likes": 0,
"type": "post",
"profile": 2
}

/v1/getPosts/{profilename}
**richiesta:**
<token>

**risposta:**
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

/v1/updatePost/{postid}
**richiesta:**
<token>
{
"title": "post modificato",
"description":"description2",
"type":"post"
}

**risposta:**
{
"id": <id>,
"title": "post modificato",
"description": "description2",
"imageUrl": null,
"createdAt": [2024,3,14,16,50,8,743859000],
"likes": 0,
"type": "post",
"profile": 2
}

_D_
/v1/deletePost/{id}
**richiesta:**
<token>

**risposta:**
Post deleted with id: 11

# Comment CRUD

_C_
/v1/newComment
**richiesta:**
{
"text": "questo è un commento",
"post":7,
"profile":2
}

**risposta:**
