**DOCUMENTAZIONE**

/api/auth/signup
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

/api/auth/signin
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

/v1/newPost
**richiesta:**
{
	"title": "post title",
    "description":"description",
    "imageUrl":null,
    "type":"post"
}
**risposta:**
{
    "id": <id>,
    "title": "post title",
    "description": "description",
    "imageUrl": null,
    "createdAt": null,
    "likes": 0,
    "type": "image",
    "profile": null,
    "comments": null
}
