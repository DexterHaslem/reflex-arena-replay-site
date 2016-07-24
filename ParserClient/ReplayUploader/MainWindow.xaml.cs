using Microsoft.Win32;
using System.Windows;

namespace ReplayUploader
{

    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        //public string Directory { get; set; }
        private ViewModel vm;

        public MainWindow()
        {
            InitializeComponent();
            DataContext = vm = new ViewModel(this);
        }

        private void OnOpenDirectoryClick(object sender, RoutedEventArgs e)
        {
            var ofd = new OpenFileDialog();
            ofd.Filter = "Reflex replays (*.rep)|*.rep";
            if (ofd.ShowDialog() != true) 
                return;
            var dir = new System.IO.FileInfo(ofd.FileName).Directory;
            vm.GetAllFilesInDirectory(dir.FullName);
        }

        private void OnUpload(object sender, RoutedEventArgs e)
        {
            vm.OnUpload();
        }
    }
}
