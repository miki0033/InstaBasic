from flask import Flask, make_response, redirect, send_file, jsonify, request, flash, url_for, Response
from flask_cors import CORS, cross_origin
import os
import uuid

from werkzeug.utils import secure_filename

baseDir = "C:/Users/cosim/Desktop/Code/GitHub/InstaBasic/resources"
pfpDir = baseDir + "/uploads/pfp"
postDir = baseDir + "/uploads/posts"

app = Flask(__name__)
CORS(app)

app.config['PFP_UPLOAD_DIR'] = pfpDir
app.config['POST_UPLOAD_DIR'] = postDir
ALLOWED_EXTENSIONS = {'txt', 'pdf', 'png', 'jpg', 'jpeg', 'gif'}


def allowed_file(filename):
    return '.' in filename and \
           filename.rsplit('.', 1)[1].lower() in ALLOWED_EXTENSIONS


def newFilename(filename):
    nameParts = filename.split(".")
    return nameParts[0]+str(uuid.uuid1())+"."+nameParts[1]


def _build_cors_preflight_response():
    response = make_response()
    response.headers.add("Access-Control-Allow-Origin", "*")
    response.headers.add('Access-Control-Allow-Headers', "*")
    response.headers.add('Access-Control-Allow-Methods', "*")
    return response


### -######- ###
###  ROUTES  ###
### -######- ###
@app.route('/')
def index():
    return "Working..."


@app.route('/get/pfp/<profileImageName>', methods=['GET', 'POST'])
def get_PFP(profileImageName):
    return send_file(pfpDir+profileImageName, mimetype="image")


@app.route('/get/post/<postImageName>', methods=['GET', 'POST'])
def get_Post(postImageName):
    return send_file(postDir+postImageName, mimetype="image")


@app.route('/post/pfp/', methods=['GET', 'POST'])
@cross_origin()
def post_PFP():
    if request.method == "OPTIONS":  # CORS preflight
        return _build_cors_preflight_response()
    elif request.method == 'POST':
        # check if the post request has the file part
        if 'file' not in request.files:
            flash('No file part')
            noFileResponse = jsonify(
                message="There is no file in request"
            )
            return noFileResponse, 410
        file = request.files['file']
        # if user does not select file, browser also
        # submit a empty part without filename
        if file.filename == '':
            flash('No selected file')
            noSelectedFileResponse = jsonify(
                message="File was not selected or has no name"
            )
            return noSelectedFileResponse, 410
        if file and allowed_file(file.filename):
            filename = secure_filename(file.filename)
            file.save(os.path.join(app.config['PFP_UPLOAD_DIR'], filename))
            response = jsonify(
                message="Image sent succesfully",
                imageURL="pfp/" + filename
            )
            return response, 200


@app.route('/post/post/', methods=['GET', 'POST', 'OPTIONS'])
@cross_origin()
def post_Post():
    if request.method == "OPTIONS":  # CORS preflight
        return _build_cors_preflight_response()
    elif request.method == 'POST':
        # check if the post request has the file part
        if 'file' not in request.files:
            flash('No file part')
            noFileResponse = jsonify(
                message="There is no file in request"
            )
            return noFileResponse, 410
        file = request.files['file']
        # if user does not select file, browser also
        # submit a empty part without filename
        if file.filename == '':
            flash('No selected file')
            noSelectedFileResponse = jsonify(
                message="File was not selected or has no name"
            )
            return noSelectedFileResponse, 410
        if file and allowed_file(file.filename):
            filename = newFilename(file.filename)
            file.save(os.path.join(
                app.config['POST_UPLOAD_DIR'], filename)
            )
            response = jsonify(
                message="Image sent succesfully",
                imageURL="posts/" + filename
            )
            return response, 200


### -#######- ###
###  APP RUN  ###
### -#######- ###
if __name__ == '__main__':
    app.secret_key = "JARM"
    app.run(host='localhost', port=4000, debug=True)
