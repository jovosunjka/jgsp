package com.mjvs.jgsp.service;

import com.mjvs.jgsp.model.Line;
import com.mjvs.jgsp.model.Schedule;
import com.mjvs.jgsp.model.Stop;
import com.mjvs.jgsp.repository.LineRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LineServiceImpl implements LineService {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Autowired
    private LineRepository lineRepository;

    @Override
    public boolean add(String lineName)
    {
        if(exists(lineName))
        {
            logger.debug(String.format("Line %s already exists.", lineName));
            return false;
        }

        try
        {
            lineRepository.save(new Line(lineName));
            logger.info(String.format("Line %s successfully added!", lineName));
        }
        catch (Exception ex)
        {
            logger.error(String.format("Error adding new line %s message %s",
                    lineName, ex.getMessage()));
            return false;
        }

        return true;
    }

    @Override
    public List<Line> getAll()
    {
        return lineRepository.findAll();
    }

    @Override
    public List<Stop> getLineStops(String lineName)
    {
        Line line = lineRepository.findByName(lineName);
        if(line == null)
        {
            logger.warn(String.format("Line %s does not exist.", lineName));
            return new ArrayList<>();
        }

        return line.getStops();
    }

    @Override
    public boolean update(String oldLineName, String newLineName) {
        return false;
    }

    @Override
    public boolean delete(String lineName)
    {
        Line line = lineRepository.findByName(lineName);
        if(line == null){
            logger.warn(String.format("Line %s does not exists.", lineName));
            return false;
        }

        try
        {
            lineRepository.delete(line);
            logger.info("Line %s successfully deleted!", lineName);
        }
        catch (Exception ex)
        {
            logger.error(String.format("Error deleting line %s message %s",
                    lineName, ex.getMessage()));
            return false;
        }

        return true;
    }

    @Override
    public Line getByName(String lineName)
    {
        return  lineRepository.findByName(lineName);
    }

    @Override
    public List<Schedule> getSchedules(String lineName)
    {
        Line line = lineRepository.findByName(lineName);
        if(line == null)
        {
            logger.warn(String.format("Line %s does not exists.", lineName));
            return new ArrayList<>();
        }

        return line.getSchedules();
    }

    @Override
    public boolean exists(String lineName)
    {
        Line line = lineRepository.findByName(lineName);
        return line != null;
    }
}
