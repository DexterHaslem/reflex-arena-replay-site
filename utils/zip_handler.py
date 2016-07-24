# replays on every other website are zipped and 5 folders deep within, eg:
# TCAFiles\Users\U\123\replays\

# use namelist to pull any rep out
import zipfile
from os import path, listdir

dir = "D:\\reflexreplays\\toprocess\\"
output_dir = "D:\\reflexreplays\\"

for file in listdir(dir):
    if not file.endswith('.zip'):
        continue

    zf = zipfile.ZipFile(path.join(dir, file), 'r')

    for n in zf.namelist():
        if not n.endswith('.rep'):
            continue

        contents = zf.read(n)
        # n contains entire folder structure so trim it here
        bn = path.basename(n)
        new_file = path.join(output_dir, bn)
        if path.isfile(new_file):
            continue
        f = open(new_file, 'wb')
        f.write(contents)
        f.close()
