/*
 * Copyright 2013 Peter Lawrey
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.openhft.chronicle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class VanillaChronicleUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(VanillaChronicleUtils.class.getName());

    public static File mkFiles(String basePath, String cycleStr, String name, boolean forAppend) throws IOException {
        File dir = new File(basePath, cycleStr);

        if (!forAppend) {
            //This test needs to be done before any directories are created.
            File f = new File(dir, name);
            if (!f.exists()) {
                throw new FileNotFoundException(f.getAbsolutePath());
            }
        }

        if (!dir.isDirectory()) {
            boolean created = dir.mkdirs();
            LOGGER.trace("Created {} is {}",dir,created);
        }

        File file = new File(dir, name);
        if (file.exists()) {
             LOGGER.trace("Opening {}", file);
        } else if (forAppend) {
             LOGGER.trace("Creating {}", file);
        } else {
            throw new FileNotFoundException(file.getAbsolutePath());
        }

        return file;
    }
}
