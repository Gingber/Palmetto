/**
 * This file is part of Palmetto.
 *
 * Palmetto is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Palmetto is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Palmetto.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.aksw.palmetto.io;

import java.io.File;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Deprecated
public class SimpleDocumentTextSupplier implements DocumentTextSupplier {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleDocumentTextSupplier.class);

    public static SimpleDocumentTextSupplier create(File inputFile) {
        Scanner scanner = null;
        SimpleDocumentTextSupplier supplier = null;
        try {
            scanner = new Scanner(inputFile);
            supplier = new SimpleDocumentTextSupplier(scanner);
        } catch (Exception e) {
            LOGGER.error("Couldn't open input file. Returning null.", e);
        }
        return supplier;
    }

    private Scanner scanner;

    private SimpleDocumentTextSupplier(Scanner scanner) {
        this.scanner = scanner;
    }

    public String getNextDocumentText() {
        String text = null;
        if (scanner != null) {
            if (scanner.hasNext()) {
                text = scanner.nextLine();
            } else {
                scanner.close();
                scanner = null;
            }
        }
        return text;
    }
}
