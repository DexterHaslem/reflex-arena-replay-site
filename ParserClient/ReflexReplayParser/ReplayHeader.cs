using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.InteropServices;
using System.Text;
using System.Threading.Tasks;


namespace ReflexReplayParser
{
    //public interface IReplayHeaderPlayer
    //{
    //    string  Name    { get; }
    //    uint    Score   { get; }
    //    uint    Team    { get; }
    //}

    //public interface IReplayHeader
    //{
    //    uint    Tag { get; }
    //    uint    ProtocolVersion { get; }
    //    uint    PlayerCount     { get; }
    //    uint    MarkerCount     { get; }
    //    string  GameMode        { get; }
    //    string  MapName         { get; }
    //    string  HostName        { get; }

    //    IReplayHeaderPlayer[] Players { get; set; }
    //}
    /*
 * #if (PROTOCOL_VERSION >= 0x3F)
#define REPLAY_TAG 0xD00D001D
#define SERVER_MAX_CLIENTS 16

struct ReplayHeaderPlayer
{
    char name[32];
    int32_t score;
    int32_t team;
    uint64_t steamId;
};

struct ReplayHeader
{
    uint32_t tag;
    uint32_t protocolVersion;
    uint32_t playerCount;
    uint32_t markerCount;
    uint32_t gameFinished;    // i.e. got to end game screen    THIS IS NOT ALWAYS PRESENT IN 0x3F
                              // 32 bits padding here
    uint32_t pudding;        // SHUT UP GCC

    uint64_t workshopId;    // if workshop map, this is the id for it
    uint64_t epochStartTime;
    char     szGameMode[64];
    char     szMapName[256];
    char     szHostName[256];
    struct ReplayHeaderPlayer    players[SERVER_MAX_CLIENTS];
}; */

    [StructLayout(LayoutKind.Sequential, CharSet = CharSet.Ansi)]
    public struct ReplayHeaderPlayer 
    {
        [MarshalAs(UnmanagedType.ByValTStr, SizeConst = 32)]
        public string name;
        public int score;
        public uint team;
        public ulong steamId;
    }

    [StructLayout(LayoutKind.Sequential, CharSet = CharSet.Ansi)]
    public struct ReplayHeader
    {
        public uint tag; // tag is pre-parsed
        public uint protocolVersion;
        public uint playerCount;
        public uint markerCount;
        public uint gameFinished;
        public uint padding;
        public ulong mapWorkshopId;
        public ulong epocTime;

        [MarshalAs(UnmanagedType.ByValTStr, SizeConst = 64)]
        public string szGameMode;
        [MarshalAs(UnmanagedType.ByValTStr, SizeConst = 256)]
        public string szMapName;
        [MarshalAs(UnmanagedType.ByValTStr, SizeConst = 256)]
        public string szHostName;

        [MarshalAs(UnmanagedType.ByValArray, ArraySubType = UnmanagedType.Struct, SizeConst = 16)]
        public ReplayHeaderPlayer[] players;
    }

    /*
    #elif (PROTOCOL_VERSION == 0x3E)
    #define REPLAY_TAG 0xD00D001C
    #define SERVER_MAX_CLIENTS 16

    struct ReplayHeaderPlayer
    {
        char name[32];
        int32_t score;
        int32_t team;
    };

    struct ReplayHeader
    {
        uint32_t tag;
        uint32_t protocolVersion;
        uint32_t playerCount;
        uint32_t markerCount;
        char     szGameMode[64];
        char     szMapName[256];
        char     szHostName[256];
        struct ReplayHeaderPlayer    players[SERVER_MAX_CLIENTS];
    };
    #endif*/
    [StructLayout(LayoutKind.Sequential, CharSet = CharSet.Ansi)]
    public struct ReplayHeaderPlayerOld //: IReplayHeaderPlayer
    {
        [MarshalAs(UnmanagedType.ByValTStr, SizeConst = 32)]
        public string name;
        public int score;
        public uint team;

        //public uint     Team => team;
        //public string   Name => name;
        //public uint     Score => score;
    }

    [StructLayout(LayoutKind.Sequential, CharSet = CharSet.Ansi)]
    public struct ReplayHeaderOld //: IReplayHeader
    {
        //public uint tag; tag is pre-parsed
        public uint protocolVersion;
        public uint playerCount;
        public uint markerCount;
        [MarshalAs(UnmanagedType.ByValTStr, SizeConst = 64)]
        public string szGameMode;
        [MarshalAs(UnmanagedType.ByValTStr, SizeConst = 256)]
        public string szMapName;
        [MarshalAs(UnmanagedType.ByValTStr, SizeConst = 256)]
        public string szHostName;

        [MarshalAs(UnmanagedType.ByValArray,  ArraySubType = UnmanagedType.Struct, SizeConst = 16)]
        public ReplayHeaderPlayerOld[] players;
    }
}
