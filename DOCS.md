**DOCUMENTAZIONE**

/api/auth/signup
**richiesta:**
{
"username": "username1",
"email":"username@gmail.com",
"password":"password1"
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
"token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VybmFtZTEiLCJpYXQiOjE3MDk3MzA5ODIsImV4cCI6MTcwOTgxNzM4Mn0.gE5ITqrThVMPsKE3FxUsdt-nIzuPqELzx_XiTe4ezWxcOj252fTVEfOIOYtGP4UOhtGqNpxzXb1khY_diU5UiA",
"type": "Bearer",
"id": 2,
"username": "username1",
"email": "username@gmail.com",
"roles": []
}
