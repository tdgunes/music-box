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
			int _alt;
			setState(24);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
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
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(12); match(STRING);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(13); match(T__0);
				setState(19);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
				while ( _alt!=2 && _alt!=ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(14); start();
						setState(15); match(OPERATOR);
						}
						} 
					}
					setState(21);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
				}
				setState(22); match(STRING);
				setState(23); match(T__1);
				}
				break;
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\7\35\4\2\t\2\3\2"+
		"\3\2\3\2\3\2\7\2\t\n\2\f\2\16\2\f\13\2\3\2\3\2\3\2\3\2\3\2\3\2\7\2\24"+
		"\n\2\f\2\16\2\27\13\2\3\2\3\2\5\2\33\n\2\3\2\2\2\3\2\2\2\37\2\32\3\2\2"+
		"\2\4\5\7\4\2\2\5\n\7\6\2\2\6\7\7\5\2\2\7\t\5\2\2\2\b\6\3\2\2\2\t\f\3\2"+
		"\2\2\n\b\3\2\2\2\n\13\3\2\2\2\13\r\3\2\2\2\f\n\3\2\2\2\r\33\7\3\2\2\16"+
		"\33\7\6\2\2\17\25\7\4\2\2\20\21\5\2\2\2\21\22\7\5\2\2\22\24\3\2\2\2\23"+
		"\20\3\2\2\2\24\27\3\2\2\2\25\23\3\2\2\2\25\26\3\2\2\2\26\30\3\2\2\2\27"+
		"\25\3\2\2\2\30\31\7\6\2\2\31\33\7\3\2\2\32\4\3\2\2\2\32\16\3\2\2\2\32"+
		"\17\3\2\2\2\33\3\3\2\2\2\5\n\25\32";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}