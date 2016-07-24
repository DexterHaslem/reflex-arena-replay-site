using System;
using System.Collections.ObjectModel;
using System.IO;
using System.Linq;
using System.Threading.Tasks;
using System.Windows;

namespace ReplayUploader
{
    public class ViewModel : NotifyPropertyChangedBase
    {
        private Window _owner;

        public ObservableCollection<ReplayFileItem> Files { get; set; }
        private string _restEndpoint;
        public string RestEndpoint
        {
            get { return _restEndpoint; }
            set { SetProperty(ref _restEndpoint, value); }
        }

        public ViewModel(Window owner)
        {
            _owner = owner;
            Files = new ObservableCollection<ReplayFileItem>();
            //RestEndpoint = "http://fragged.online:8081/replayhub/addParsed";
            RestEndpoint = "http://localhost:8080/replayhub/addParsed";
        }

        public void GetAllFilesInDirectory(string path)
        {
            Files.Clear();
            foreach (var file in Directory.GetFiles(path, "*.rep", SearchOption.AllDirectories))
                Files.Add(new ReplayFileItem(file, true));
        }

        internal void OnUpload()
        {
            var queue = Files.Where(f => f.IsSelected && f.Status < UploadStatus.Uploaded);
            foreach (var f in queue)
                f.Status = UploadStatus.Queued;
            // after some goose chase stuff, i realized, teh serevr is not thread safe
            //Parallel.ForEach(queue, rpi=> 
            foreach (var rpi in queue)
            {
                rpi.Status = UploadStatus.Uploading;
                Uploader.UploadToServer(RestEndpoint, rpi.Name, rpi.Path);
                rpi.Status = UploadStatus.Uploaded;
            }//);
        }
    }
}
