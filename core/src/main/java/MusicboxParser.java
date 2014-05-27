// Generated from /Users/tdgunes/Projects/music-bot/core/src/main/antlr/Musicbox.g4 by ANTLR 4.x
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MusicboxParser extends Parser {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__1=1, T__0=2, OPERATOR=3, STRING=4, WS=5;
	public static final String[] tokenNames = {
		"<INVALID>", "')'", "'('", "OPERATOR", "STRING", "WS"
	};
	public static final int
		RULE_start = 0;
	public static final String[] ruleNames = {
		"start"
	};

	@Override
	public String getGrammarFileName() { return "Musicbox.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public MusicboxParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class StartContext extends ParserRuleContext {
		public StartContext start(int i) {
			return getRuleContext(StartContext.class,i);
		}
		public TerminalNode STRING() { return getToken(MusicboxParser.STRING, 0); }
		public TerminalNode OPERATOR(int i) {
			return getToken(MusicboxParser.OPERATOR, i);
		}
		public List<StartContext> start() {
			return getRuleContexts(StartContext.class);
		}
		public List<TerminalNode> OPERATOR() { return getTokens(MusicboxParser.OPERATOR); }
		public StartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MusicboxListener ) ((MusicboxListener)listener).enterStart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MusicboxListener ) ((MusicboxListener)listener).exitStart(this);
		}
	}

	public final StartContext start() throws RecognitionException {
		StartContext _localctx = new StartContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_start);
		int _la;
		try {
			setState(13);
			switch (_input.LA(1)) {
			case T__0:
				enterOuterAlt(_localctx, 1);
				{
				setState(2); match(T__0);
				setState(3); match(STRING);
				setState(8);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==OPERATOR) {
					{
					{
					setState(4); match(OPERATOR);
					setState(5); start();
					}
					}
					setState(10);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(11); match(T__1);
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 2);
				{
				setState(12); match(STRING);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\7\22\4\2\t\2\3\2"+
		"\3\2\3\2\3\2\7\2\t\n\2\f\2\16\2\f\13\2\3\2\3\2\5\2\20\n\2\3\2\2\2\3\2"+
		"\2\2\22\2\17\3\2\2\2\4\5\7\4\2\2\5\n\7\6\2\2\6\7\7\5\2\2\7\t\5\2\2\2\b"+
		"\6\3\2\2\2\t\f\3\2\2\2\n\b\3\2\2\2\n\13\3\2\2\2\13\r\3\2\2\2\f\n\3\2\2"+
		"\2\r\20\7\3\2\2\16\20\7\6\2\2\17\4\3\2\2\2\17\16\3\2\2\2\20\3\3\2\2\2"+
		"\4\n\17";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}