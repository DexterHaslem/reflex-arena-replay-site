from flask import Flask

app = Flask(__name__)

# on actual server
#replay_dir = "C:\\steamcmd\\reflex_ds\\replays"
replay_dir = 'testreplays'

@app.route('/')
def hello_world():
    return 'Hello World: ' + replay_dir


if __name__ == '__main__':
    app.run()
