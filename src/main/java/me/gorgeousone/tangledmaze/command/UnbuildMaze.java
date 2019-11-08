package me.gorgeousone.tangledmaze.command;

import me.gorgeousone.tangledmaze.command.framework.argument.ArgType;
import me.gorgeousone.tangledmaze.command.framework.argument.ArgValue;
import me.gorgeousone.tangledmaze.command.framework.argument.Argument;
import me.gorgeousone.tangledmaze.command.framework.command.ArgCommand;
import me.gorgeousone.tangledmaze.util.PlaceHolder;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.gorgeousone.tangledmaze.core.Maze;
import me.gorgeousone.tangledmaze.data.Messages;
import me.gorgeousone.tangledmaze.handler.BuildHandler;
import me.gorgeousone.tangledmaze.handler.MazeHandler;

public class UnbuildMaze extends ArgCommand {
	
	public UnbuildMaze(MazeCommand mazeCommand) {
		super("unbuild", null, mazeCommand);
		addArg(new Argument("part", ArgType.STRING, new ArgValue(ArgType.STRING, "maze"), "maze", "floor", "roof"));
	}

	@Override
	protected boolean onExecute(CommandSender sender, ArgValue[] arguments) {

		Player player = (Player) sender;
		Maze maze = MazeHandler.getMaze(player);

		if(!maze.isConstructed()) {
			Messages.MESSAGE_NO_MAZE_TO_UNBUILD.sendTo(player);
			return true;
		}

		String mazePart = arguments[0].getString();

		switch (mazePart) {

			case "floor":
				BuildHandler.unbuildFloor(maze);
				break;

			case "roof":
				BuildHandler.unbuildRoof(maze);
				break;

			case "maze":
				BuildHandler.unbuildMaze(maze);
				Messages.MESSAGE_MAZE_UNBUILDING_STARTED.sendTo(player);
				break;

			default:
				Messages.ERROR_INVALID_MAZE_PART.sendTo(player, new PlaceHolder("mazepart", mazePart));
				break;
		}
		return true;
	}
}