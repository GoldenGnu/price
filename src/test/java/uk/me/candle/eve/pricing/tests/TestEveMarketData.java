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
package uk.me.candle.eve.pricing.tests;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import javax.xml.parsers.ParserConfigurationException;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.Test;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import uk.me.candle.eve.pricing.Pricing;
import uk.me.candle.eve.pricing.PricingFactory;
import uk.me.candle.eve.pricing.impl.EveMarketData;
import uk.me.candle.eve.pricing.options.LocationType;
import uk.me.candle.eve.pricing.options.PricingFetch;
import uk.me.candle.eve.pricing.options.impl.DefaultPricingOptions;

/**
 *
 * @author Candle
 */
public class TestEveMarketData extends PricingTests {

    @Test @Ignore
    public void testGetPriceOnlineRegion() {
        Pricing pricing = PricingFactory.getPricing(new DefaultPricingOptions() {
            @Override
            public List<Long> getLocations() {
                return Collections.singletonList(10000002L);
            }
            @Override
            public LocationType getLocationType() {
                return LocationType.REGION;
            }
            @Override
            public PricingFetch getPricingFetchImplementation() {
                return PricingFetch.EVE_MARKETDATA;
            }
        });
        testAll(pricing);
    }

    @Test @Ignore
    public void testGetPriceOnlineSystem() {
        Pricing pricing = PricingFactory.getPricing(new DefaultPricingOptions() {
            @Override
            public List<Long> getLocations() {
                return Collections.singletonList(30000142L);
            }
            @Override
            public LocationType getLocationType() {
                return LocationType.SYSTEM;
            }
            @Override
            public PricingFetch getPricingFetchImplementation() {
                return PricingFetch.EVE_MARKETDATA;
            }
        });
        testAll(pricing);
    }

    @Test @Ignore
    public void testGetPriceOnlineStation() {
        Pricing pricing = PricingFactory.getPricing(new DefaultPricingOptions() {
            @Override
            public List<Long> getLocations() {
                return Collections.singletonList(60003760L);
            }
            @Override
            public LocationType getLocationType() {
                return LocationType.STATION;
            }
            @Override
            public PricingFetch getPricingFetchImplementation() {
                return PricingFetch.EVE_MARKETDATA;
            }
        });
        testAll(pricing);
    }

    @Test @Ignore
    public void testGetPriceFail() {
        System.out.println("Testing EVE_MARKETDATA errors");
        final EveMarketData pricing = new EveMarketDataEmptyDummy();
        pricing.setPricingOptions(new DefaultPricingOptions() {
            @Override
            public List<Long> getLocations() {
                return Collections.singletonList(10000002L);
            }
            @Override
            public LocationType getLocationType() {
                return LocationType.REGION;
            }
            @Override
            public PricingFetch getPricingFetchImplementation() {
                return PricingFetch.EVE_MARKETDATA;
            }
        });
        pricing.setPrice(34, -1d);
        Set<Integer> failed = synchronousPriceFetch(pricing, 34);
        assertEquals(failed.size(), 1);
    }

    class EveMarketDataEmptyDummy extends EveMarketData {

        public EveMarketDataEmptyDummy() {
            super(1);
        }

        @Override
        protected Element getElement(URL url) throws SocketTimeoutException, IOException, ParserConfigurationException, SAXException {
            throw new ParserConfigurationException("TESTING EXCEPTION");
        }

    }
}
