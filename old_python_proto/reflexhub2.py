from flask import Flask, render_template, send_from_directory
from replays import get_replays, get_replays_json, get_replays_in_dir, REPLAY_EXT

app = Flask(__name__)
app.config['DEBUG'] = True
#app.config['SERVER_NAME'] = "fragged.online:80"
# beware: when running from pycharm, cwd is C:\Program Files (x86)\JetBrains\PyCharm 2016.1.4\jre\jre\bin
# unless you set it in run configurations
app.config['REPLAYDIR'] = 'testreplays'

# on actual server
#app.config['REPLAYDIR'] = "C:\\steamcmd\\reflex_ds\\replays"

@app.route('/')
def index():
    return file_list()

@app.route('/parsed')
def parsed():
    return render_template('parsed.html', replays=get_replays(app.config['REPLAYDIR']))

@app.route('/fileList')
def file_list():
    return render_template('filelist.html', replays=get_replays_in_dir(app.config['REPLAYDIR']))

@app.route('/about')
def about():
    return render_template('about.html')

@app.route('/getReplaysJson')
def get_replays_rest():
    replay_json = get_replays_json(app.config['REPLAYDIR'])
    return replay_json


@app.route('/sendFile/<name>')
def send_file(name):
    return send_from_directory(app.config['REPLAYDIR'], name + '.' + REPLAY_EXT)

if __name__ == '__main__':
    app.run(debug=True)
