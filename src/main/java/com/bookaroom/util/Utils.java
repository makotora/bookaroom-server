package com.bookaroom.util;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.joda.time.DateTime;
import org.joda.time.Days;

import com.bookaroom.exceptions.InvalidDateRangeException;
import com.bookaroom.web.dto.AvailabilityRange;

public class Utils
{
    public static Collection<Date> getAllDatesFromTo(Date from, Date to)
        throws InvalidDateRangeException
    {
        List<Date> dates = new ArrayList<Date>();

        if (from.after(to)) {
            throw new InvalidDateRangeException();
        }

        Calendar currentDateCal = Calendar.getInstance();
        currentDateCal.setTime(from);
        Date currentDate = currentDateCal.getTime();

        while (!currentDate.after(to)) {
            dates.add(currentDate);

            currentDateCal.add(Calendar.DAY_OF_MONTH, 1);
            currentDate = currentDateCal.getTime();
        }

        return dates;
    }

    public static List<AvailabilityRange> buildAvailabilityRanges(List<Date> availabilities)
    {
        List<AvailabilityRange> availabilityRanges = new ArrayList<AvailabilityRange>();

        if (availabilities.isEmpty()) {
            return availabilityRanges;
        }

        // Sort availability dates ascending
        availabilities.sort((av1, av2) -> av1.compareTo(av2));

        Date currentFrom = availabilities.get(0);
        Date currentTo = currentFrom;

        for (int i = 1; i < availabilities.size(); i++) {
            Date loopDate = availabilities.get(i);
            int daysBetween = Days.daysBetween(new DateTime(currentTo), new DateTime(loopDate)).getDays();
            if (daysBetween == 1) {
                currentTo = loopDate;
            }
            else {
                availabilityRanges.add(new AvailabilityRange(currentFrom, currentTo));
                currentFrom = loopDate;
                currentTo = loopDate;
            }
        }

        availabilityRanges.add(new AvailabilityRange(currentFrom, currentTo));

        return availabilityRanges;
    }

    public static String prepareResourcePathForServerFile(
        String filesRootPath,
        String filePath,
        String serverResourceHandlerPath)
    {
        final Path parentDirectoryPath = Paths.get(filesRootPath);
        return FilenameUtils.separatorsToUnix(Paths.get(serverResourceHandlerPath,
                                                        parentDirectoryPath.relativize(Paths.get(filePath))
                                                                           .toString())
                                                   .toString());
    }
}
