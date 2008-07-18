/**
 *
 */
package org.geotools.utils;

import java.io.File;

import org.apache.commons.cli2.Argument;
import org.apache.commons.cli2.CommandLine;
import org.apache.commons.cli2.Group;
import org.apache.commons.cli2.Option;
import org.apache.commons.cli2.builder.ArgumentBuilder;
import org.apache.commons.cli2.builder.DefaultOptionBuilder;
import org.apache.commons.cli2.builder.GroupBuilder;
import org.apache.commons.cli2.commandline.Parser;
import org.apache.commons.cli2.util.HelpFormatter;


/**
 * @author jezekjan
 *
 */
public class TransGen {
    private static Group createModel() {
        DefaultOptionBuilder oBuilder = new DefaultOptionBuilder();
        ArgumentBuilder aBuilder = new ArgumentBuilder();
        GroupBuilder gBuilder = new GroupBuilder();

        /**
         *  OUTPUT Option
         */
        Argument outputPath = aBuilder.withName("output path").withMinimum(1).withMaximum(1).create();
        Option outputOption = oBuilder.withLongName("output")
                                      .withDescription("Path to generated output file")
                                      .withArgument(outputPath).create();

        /**
         *  GCP Option
         */
        Argument GCPPath = aBuilder.withName("path").withMinimum(1).withMaximum(1).create();
        Option inputOption = oBuilder.withLongName("input").withDescription("File with GCPs")
                                     .withArgument(GCPPath).create();

        /**
         * Set skew option
         */
        Argument skewArg = aBuilder.withName("skew").withMinimum(1).withMaximum(1).create();
        Option setSkew = oBuilder.withLongName("skew")
                                 .withDescription("Sets exlipcitly the value of skew parameter ")
                                 .withArgument(skewArg).create();

        /**
         * Set rotation option
         */
        Argument phiArg = aBuilder.withName("rotation").withMinimum(1).withMaximum(1).create();
        Option setPhi = oBuilder.withLongName("phi")
                                .withDescription("Sets exlipcitly the value of rotation parameter (in radians)")
                                .withArgument(phiArg).create();

        Option statistics = oBuilder.withLongName("s")
                                    .withDescription("Choose to get detail error statistics")
                                    .create();

        Option geom = oBuilder.withLongName("g")
                              .withDescription("Choose to get geometric coeficients").create();

        Option similar = oBuilder.withLongName("similar")
                                 .withDescription("Choose to get similar transformation (skew = 0, scale equal for both axis). "
                + "If --similar is choosen --skew and --phi parameters are ignored").create();

        // configure the options
        Group options = gBuilder.withName("gcps2wld:").withOption(inputOption).withOption(similar)
                                .withOption(setSkew).withOption(setPhi).withOption(statistics)
                                .withOption(geom).withMinimum(1).create();

        return options;
    }

    /**
     * Writes output according to comandline options
     * @param cl
     * @throws Exception
     */
    public static void getOutput(CommandLine cl) throws Exception {
        Builder builder = null;

        if (cl.hasOption("--input")) {
            // grab the path
            String path = (String) cl.getValue("--input");
            builder = new Builder(new File(path));

            if (cl.hasOption("--skew")) {
                double skew = (new Double((String) cl.getValue("--skew")).doubleValue());
                builder.setSkew(skew);
            }

            if (cl.hasOption("--phix")) {
                double phix = (new Double((String) cl.getValue("--phix")).doubleValue());
                builder.setPhix(phix);
            }
            
            if (cl.hasOption("--phiy")) {
                double phiy = (new Double((String) cl.getValue("--phiy")).doubleValue());
                builder.setPhiy(phiy);
            }

            if (cl.hasOption("--similar")) {
                builder.setSimilar();
            }
        } else {
            throw new NullPointerException("--input parameter is missing");
        }

        builder.printResut();

        if (cl.hasOption("--g")) {
            builder.printGeomCoef();
        }

        if (cl.hasOption("--s")) {
            builder.printStatistics();
        }
    }

    public static void main(String[] args) {
        Group group = createModel();

        // configure a HelpFormatter
        HelpFormatter hf = new HelpFormatter();

        // configure a parser
        Parser p = new Parser();
        p.setGroup(group);
        p.setHelpFormatter(hf);
        p.setHelpTrigger("--help");

        CommandLine cl = p.parseAndHelp(args);

        // abort application if no CommandLine was parsed
        hf.setGroup(group);

        if (cl == null) {
            //hf.print();
            System.exit(-1);
        }

        try {
            getOutput(cl);
        } catch (Exception e) {
            System.out.println("Wrong input: " + e.getMessage());
            e.printStackTrace();
            hf.print();
        }
    }
}
