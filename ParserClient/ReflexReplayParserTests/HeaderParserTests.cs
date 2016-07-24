using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace ReflexReplayParser.Tests
{
    [TestClass()]
    public class HeaderParserTests
    {
        [TestMethod()]
        public void ParseHeaderTest()
        {
            const string testFile = "test.rep";
            var ret = HeaderParser.Parse(testFile);
            Assert.IsNotNull(ret);
        }
    }
}