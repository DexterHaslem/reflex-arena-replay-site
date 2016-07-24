using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using ReflexReplayParser;
using System.Web.Script.Serialization;
using System.Net;
using System.IO;
using System.Diagnostics;
using System.Net.Http;

namespace ReplayUploader
{
    public class Uploader
    {
        // these need to match server side DTOs to work correctly
        // kinda sucks it matches the java ones
        // simple properties
        private class ReplayInfoDTO
        {
            public uint playerCount;

            public ulong timestamp; // epoc

            public string gameMode;

            public string mapName;

            public ulong mapWorkshopId;

            public string hostName;

            // additional stuff a client that parsed itself would pass in

            public string fileName;

            public int fileSize;

            //public byte[] fileContents;

            public List<ReplayInfoPlayerDTO> players;

            public ReplayInfoDTO(string fileName, ReplayHeader header)
            {
                playerCount = header.playerCount;
                timestamp = header.epocTime;
                gameMode = header.szGameMode;
                mapName = header.szMapName;
                mapWorkshopId = header.mapWorkshopId;
                hostName = header.szHostName;
                this.fileName = fileName;
                //this.fileContents = fileContents;
                players = new List<ReplayInfoPlayerDTO>();
                foreach (var p in header.players)
                {
                    players.Add(new ReplayInfoPlayerDTO(p));
                }
            }
        }

        private class ReplayInfoPlayerDTO
        {
            public string name;
            public int score;
            public uint team;
            public ulong steamId;
            public ReplayInfoPlayerDTO(ReplayHeaderPlayer player)
            {
                name = player.name;
                score = player.score;
                team = player.team;
                steamId = player.steamId;
            }
        }

        public static async void UploadToServer(string url, string fileName, string fullPath)
        {
            try
            {
                // part 1 : metadata
                ReplayHeader header = HeaderParser.Parse(fullPath);
                
                ReplayInfoDTO dto = new ReplayInfoDTO(fileName, header);
                //if (header.mapWorkshopId < 1)
                //    Debugger.Break();
                var serializer = new JavaScriptSerializer();
                //serializer.MaxJsonLength = contents. * 2;
                string json = serializer.Serialize(dto);

                string ret = PostToServer(url, json);
                if (ret == "true")
                {
                    // now send actual file contents
                    // TODO: hardcoded url
                    // dont send file contents for now, seed w/ metadata only
                    //UploadFileContents("http://localhost:8080/replayhub/addFile", fullPath);
                }
            }
            catch (Exception ex)
            {
            }
        }

        private static bool UploadFileContents(string url, string fullPath)
        {
            try
            {
                byte[] contents = File.ReadAllBytes(fullPath);
                using (var wc = new WebClient())
                {
                    wc.QueryString.Add("filename",new FileInfo(fullPath).Name);
                    wc.UploadData(url, contents);
                }
                return true;
            }
            catch
            {
                return false;
            }
            //using (var client = new HttpClient())
            //{
            //    
            //    var formDataContent = new MultipartFormDataContent();
            //    var streamContent = new StreamContent(File.Open(fullPath, FileMode.Open));
            //    formDataContent.Add(streamContent, "file", new FileInfo(fullPath).Name);
            //    return await client.PostAsync(url, formDataContent);
            //}
        }

        private static string PostToServer(string url, string json)
        {
            var request = (HttpWebRequest)WebRequest.Create(url);
            request.ContentType = "application/json";
            request.Method = "POST";
            using (var sw = new StreamWriter(request.GetRequestStream()))
                sw.Write(json);
            var response = (HttpWebResponse)request.GetResponse();
            using (var sr = new StreamReader(response.GetResponseStream()))
            {
                var result = sr.ReadToEnd();
                return result;
            }
        }
    }
}
