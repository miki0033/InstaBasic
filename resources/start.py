from flask import Flask, send_file
from markupsafe import escape

app = Flask(__name__)


@app.route('/')
def index():
    return "Working..."


@app.route('/pfp/get/<profileImageName>')
def get_PFP(profileImageName):
    return send_file("pfp\\"+profileImageName, mimetype="image")


@app.route('/pfp/post/<profileImageName>')
def post_PFP(profileImageName):
    return send_file("pfp\\"+profileImageName, mimetype="image")


if __name__ == '__main__':
    app.run(debug=True)
