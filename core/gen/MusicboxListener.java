// Generated from /Users/tdgunes/Projects/music-bot/core/src/main/antlr/Musicbox.g4 by ANTLR 4.x
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MusicboxParser}.
 */
public interface MusicboxListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MusicboxParser#field}.
	 * @param ctx the parse tree
	 */
	void enterField(@NotNull MusicboxParser.FieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link MusicboxParser#field}.
	 * @param ctx the parse tree
	 */
	void exitField(@NotNull MusicboxParser.FieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link MusicboxParser#fieldlist}.
	 * @param ctx the parse tree
	 */
	void enterFieldlist(@NotNull MusicboxParser.FieldlistContext ctx);
	/**
	 * Exit a parse tree produced by {@link MusicboxParser#fieldlist}.
	 * @param ctx the parse tree
	 */
	void exitFieldlist(@NotNull MusicboxParser.FieldlistContext ctx);
	/**
	 * Enter a parse tree produced by {@link MusicboxParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(@NotNull MusicboxParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link MusicboxParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(@NotNull MusicboxParser.StartContext ctx);
}