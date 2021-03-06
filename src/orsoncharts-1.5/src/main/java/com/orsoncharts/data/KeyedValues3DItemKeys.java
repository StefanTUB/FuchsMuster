/* ===========================================================
 * Orson Charts : a 3D chart library for the Java(tm) platform
 * ===========================================================
 * 
 * (C)opyright 2013-2016, by Object Refinery Limited.  All rights reserved.
 * 
 * http://www.object-refinery.com/orsoncharts/index.html
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * [Oracle and Java are registered trademarks of Oracle and/or its affiliates. 
 * Other names may be trademarks of their respective owners.]
 * 
 * If you do not wish to be bound by the terms of the GPL, an alternative
 * commercial license can be purchased.  For details, please see visit the
 * Orson Charts home page:
 * 
 * http://www.object-refinery.com/orsoncharts/index.html
 * 
 */

package com.orsoncharts.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.orsoncharts.util.ArgChecks;

/**
 * Utility methods related to the {@link KeyedValues3DItemKey} class.
 * 
 * @since 1.3
 */
public class KeyedValues3DItemKeys {
    
    private KeyedValues3DItemKeys() {
        // no need to instantiate this
    }
 
    /**
     * Returns a collection containing all the item keys for the specified
     * series.
     * 
     * @param data  the data ({@code null} not permitted).
     * @param seriesKey  the series key ({@code null} not permitted).
     * 
     * @return A collection of item keys (never {@code null}).
     */
    public static Collection<KeyedValues3DItemKey> itemKeysForSeries(
            KeyedValues3D data, Comparable<?> seriesKey) {
        ArgChecks.nullNotPermitted(data, "data");
        ArgChecks.nullNotPermitted(seriesKey, "seriesKey");
        Collection<KeyedValues3DItemKey> result 
                = new ArrayList<KeyedValues3DItemKey>();
        if (!data.getSeriesKeys().contains(seriesKey)) {
            return result;
        }
        for (Comparable<?> rowKey: (List<Comparable<?>>) data.getRowKeys()) {
            for (Comparable columnKey: 
                    (List<Comparable<?>>) data.getColumnKeys()) {
                KeyedValues3DItemKey key = new KeyedValues3DItemKey(seriesKey, 
                        rowKey, columnKey);
                result.add(key);
            }
        }
        return result;
    }
    
    /**
     * Returns a collection containing all the item keys for the specified
     * row.
     * 
     * @param data  the data ({@code null} not permitted).
     * @param rowKey  the row key ({@code null} not permitted).
     * 
     * @return A collection of item keys (never {@code null}).
     */
    public static Collection<KeyedValues3DItemKey> itemKeysForRow(
            KeyedValues3D data, Comparable<?> rowKey) {
        ArgChecks.nullNotPermitted(data, "data");
        ArgChecks.nullNotPermitted(rowKey, "rowKey");
        Collection<KeyedValues3DItemKey> result 
                = new ArrayList<KeyedValues3DItemKey>();
        if (!data.getRowKeys().contains(rowKey)) {
            return result;
        }
        for (Comparable<?> seriesKey: 
                (List<Comparable<?>>) data.getSeriesKeys()) {
            for (Comparable columnKey: (List<Comparable<?>>) 
                    data.getColumnKeys()) {
                KeyedValues3DItemKey key = new KeyedValues3DItemKey(seriesKey, 
                        rowKey, columnKey);
                result.add(key);
            }
        }
        return result;
    }
    
    /**
     * Returns a collection containing all the item keys for the specified
     * column.
     * 
     * @param data  the data ({@code null} not permitted).
     * @param columnKey  the column key ({@code null} not permitted).
     * 
     * @return A collection of item keys (never {@code null}).
     */
    public static Collection<KeyedValues3DItemKey> itemKeysForColumn(
            KeyedValues3D data, Comparable<?> columnKey) {
        ArgChecks.nullNotPermitted(data, "data");
        ArgChecks.nullNotPermitted(columnKey, "columnKey");
        Collection<KeyedValues3DItemKey> result 
                = new ArrayList<KeyedValues3DItemKey>();
        if (!data.getColumnKeys().contains(columnKey)) {
            return result;
        }
        for (Comparable<?> seriesKey: 
                (List<Comparable<?>>) data.getSeriesKeys()) {
            for (Comparable rowKey: (List<Comparable<?>>) data.getRowKeys()) {
                KeyedValues3DItemKey key = new KeyedValues3DItemKey(seriesKey, 
                        rowKey, columnKey);
                result.add(key);
            }
        }
        return result;
    }
    
}
