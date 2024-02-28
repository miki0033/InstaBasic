from flask import Flask, send_file
from markupsafe import escape

app = Flask(__name__)


@app.route('/')
def index():
    return "Working..."


@app.route('/pfp/get/<profileImageName>')
def get_PFP(profileImageName):
    return send_file("pfp/"+profileImageName, mimetype="image")


@app.route('/pfp/post/<profileImageName>')
def post_PFP(profileImageName):
    return "WIP"


@app.route('/post/get/<postResourceName>')
def get_Post(postResourceName):
    return send_file("posts/"+postResourceName, mimetype="image")


@app.route('/post/post/<postResourceName>')
def post_Post(postResourceName):
    return "WIP"


if __name__ == '__main__':
    app.run(host='localhost', port=4000, debug=True)
