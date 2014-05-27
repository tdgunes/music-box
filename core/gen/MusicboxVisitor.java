// Generated from /Users/tdgunes/Projects/music-bot/core/src/main/antlr/Musicbox.g4 by ANTLR 4.x
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link MusicboxParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface MusicboxVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link MusicboxParser#field}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitField(@NotNull MusicboxParser.FieldContext ctx);
	/**
	 * Visit a parse tree produced by {@link MusicboxParser#fieldlist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFieldlist(@NotNull MusicboxParser.FieldlistContext ctx);
	/**
	 * Visit a parse tree produced by {@link MusicboxParser#start}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStart(@NotNull MusicboxParser.StartContext ctx);
}