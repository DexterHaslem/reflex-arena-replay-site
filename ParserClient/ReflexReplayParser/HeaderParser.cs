using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;
using System.Runtime.InteropServices;

namespace ReflexReplayParser
{
    public class HeaderParser
    {
        
        public static ReplayHeader Parse(string fileName)
        {
            using (FileStream fs = File.OpenRead(fileName))
            {
                // note: binary reader is always little endian
                using (BinaryReader br = new BinaryReader(fs))
                {
                    //uint tag = br.ReadUInt32();
                    // TODO: tag handling
                    int totalSize = Marshal.SizeOf(typeof(ReplayHeader));
                    byte[] bytes = br.ReadBytes(totalSize);
                    GCHandle handle = GCHandle.Alloc(bytes, GCHandleType.Pinned);
                    try
                    {
                        ReplayHeader header = Marshal.PtrToStructure<ReplayHeader>(handle.AddrOfPinnedObject());
                        // the header player thing will always read out full size, we can trim it up here
                        //header.playerCount
                        ReplayHeaderPlayer[] players = new ReplayHeaderPlayer[header.playerCount];
                        Array.Copy(header.players, players, header.playerCount);
                        header.players = players;
                        return header;
                    }
                    finally
                    {
                        handle.Free();
                    }
                }
            }
        }

        // TODO: generic, blah dont really care
        /*
         *  // fuck that
        //public static H Parse<H,P>(string fileName) where H : IReplayHeader where P : IReplayHeaderPlayer
        public static IReplayHeader Parse<IReplayHeader,IReplayHeaderPlayer>(string fileName)
        {
            using (FileStream fs = File.OpenRead(fileName))
            {
                // note: binary reader is always little endian
                using (BinaryReader br = new BinaryReader(fs))
                {
                    int totalSize = Marshal.SizeOf(typeof(IReplayHeader));
                    byte[] bytes = br.ReadBytes(totalSize);
                    GCHandle handle = GCHandle.Alloc(bytes, GCHandleType.Pinned);
                    try
                    {
                        IReplayHeader header = Marshal.PtrToStructure<IReplayHeader>(handle.AddrOfPinnedObject());
                        // the header player thing will always read out full size, we can trim it up here
                        //header.playerCount
                        //IReplayHeaderPlayer[] players = new IReplayHeaderPlayer[header.PlayerCount];
                        //Array.Copy(header.Players, players, header.PlayerCount);
                        //header.Players = players;
                        return header;
                    }
                    finally
                    {
                        handle.Free();
                    }
                }
            }
        }*/

        public static ReplayHeaderOld ParseOld(string fileName)
        {
            using (FileStream fs = File.OpenRead(fileName))
            {
                // note: binary reader is always little endian
                using (BinaryReader br = new BinaryReader(fs))
                {
                    int totalSize = Marshal.SizeOf(typeof(ReplayHeaderOld));
                    byte[] bytes = br.ReadBytes(totalSize);
                    GCHandle handle = GCHandle.Alloc(bytes, GCHandleType.Pinned);
                    try
                    {
                        ReplayHeaderOld header = Marshal.PtrToStructure<ReplayHeaderOld>(handle.AddrOfPinnedObject());
                        // the header player thing will always read out full size, we can trim it up here
                        //header.playerCount
                        ReplayHeaderPlayerOld[] players = new ReplayHeaderPlayerOld[header.playerCount];
                        Array.Copy(header.players, players, header.playerCount);
                        header.players = players;
                        return header;
                    }
                    finally
                    {
                        handle.Free();
                    }
                }
            }
        }
    }
}
