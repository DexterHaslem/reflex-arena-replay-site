using System.IO;

namespace ReplayUploader
{
    public enum UploadStatus
    {
        Ready,
        Queued,
        Uploading,
        Uploaded,
        Error
    }

    public class ReplayFileItem : NotifyPropertyChangedBase
    {
        private string path;
        public string Path => path;

        private UploadStatus status;
        public UploadStatus Status
        {
            get { return status; }
            set { SetProperty(ref status, value); }
        }

        private bool isSelected;
        public bool IsSelected
        {
            get { return isSelected; }
            set { SetProperty(ref isSelected, value); }
        }

        public string Name => new FileInfo(path).Name;

        public ReplayFileItem(string fullPath, bool isSelected)
        {
            path = fullPath;
            this.isSelected = isSelected;
        }
    }
}
