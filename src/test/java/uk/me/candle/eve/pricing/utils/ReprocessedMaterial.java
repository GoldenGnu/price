/*
 * Copyright 2015-2016, Niklas Kyster Rasmussen, Flaming Candle
 *
 * This file is part of Price
 *
 * Price is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * Price is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Price; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 *
 */
package uk.me.candle.eve.pricing.utils;


public class ReprocessedMaterial {
    private final int typeID; //TypeID : int
    private final int quantity;
    private final int portionSize;

    public ReprocessedMaterial(final int typeID, final int quantity, final int portionSize) {
        this.typeID = typeID;
        this.quantity = quantity;
        this.portionSize = portionSize;
    }

    public int getTypeID() {
        return typeID;
    }

    public int getPortionSize() {
        return portionSize;
    }

    public int getQuantity() {
        return quantity;
    }
}
