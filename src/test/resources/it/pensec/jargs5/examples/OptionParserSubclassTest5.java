/**
 * Copyright (c) 2001-2003 Steve Purcell.
 * Copyright (c) 2002      Vidar Holen.
 * Copyright (c) 2002      Michal Ceresna.
 * Copyright (c) 2005      Ewan Mellor.
 * Copyright (c) 2010-2011 penSec.IT UG (haftungsbeschränkt)
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer. Redistributions in binary
 * form must reproduce the above copyright notice, this list of conditions and
 * the following disclaimer in the documentation and/or other materials provided
 * with the distribution. Neither the name of the copyright holder nor the names
 * of its contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */

package it.pensec.jargs5.examples;

import it.pensec.jargs5.CmdLineParser;

public class OptionParserSubclassTest5 {

	private static class MyOptionsParser extends CmdLineParser {

		public static final Option<Boolean> VERBOSE = new CmdLineParser.Option.BooleanOption('v', "verbose");
		public static final Option<Integer> SIZE = new CmdLineParser.Option.IntegerOption('s', "size");
		public static final Option<String> NAME = new CmdLineParser.Option.StringOption('n', "name");
		public static final Option<Double> FRACTION = new CmdLineParser.Option.DoubleOption('f', "fraction");

		public MyOptionsParser() {
			super();
			addOption(VERBOSE);
			addOption(SIZE);
			addOption(NAME);
			addOption(FRACTION);
		}
	}

	private static void printUsage() {
		System.err.println("usage: prog [{-v,--verbose}] [{-n,--name} a_name]"
				+ "[{-s,--size} a_number] [{-f,--fraction} a_float]");
	}

	public static void main(String[] args) {
		MyOptionsParser myOptions = new MyOptionsParser();

		try {
			myOptions.parse(args);
		} catch (CmdLineParser.UnknownOptionException e) {
			System.err.println(e.getMessage());
			printUsage();
			System.exit(2);
		} catch (CmdLineParser.IllegalOptionValueException e) {
			System.err.println(e.getMessage());
			printUsage();
			System.exit(2);
		}

		CmdLineParser.Option[] allOptions =	new CmdLineParser.Option[]{
				MyOptionsParser.VERBOSE,
				MyOptionsParser.NAME,
				MyOptionsParser.SIZE,
				MyOptionsParser.FRACTION
		};

		for (int j = 0; j < allOptions.length; ++j) {
			System.out.println(allOptions[j].longForm() + ": "
					+ myOptions.getOptionValue(allOptions[j]));
		}

		String[] otherArgs = myOptions.getRemainingArgs();
		System.out.println("remaining args: ");
		for (int i = 0; i < otherArgs.length; ++i) {
			System.out.println(otherArgs[i]);
		}
		System.exit(0);
	}
}
