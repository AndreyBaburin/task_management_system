package com.andrey_baburin.data;

import com.andrey_baburin.entity.Task;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Repository
@Qualifier("Hibernate")
public class TaskHibernateDAO implements TaskDAO {

    private final SessionFactory sessionFactory;

    @Transactional
    @Override
    public void addTask(Task t) {
        Session session = sessionFactory.getCurrentSession();
        session.save(t);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Task> getAllTasks() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select t from Task t", Task.class).getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public Task getTaskById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Task.class, id);
    }

    @Transactional
    @Override
    public void updateTask(Task t) {
        Session session = sessionFactory.getCurrentSession();
        Task taskToBeUpdated = session.get(Task.class, t.getId());

        taskToBeUpdated.setTitle(t.getTitle());
        taskToBeUpdated.setDescription(t.getDescription());
        taskToBeUpdated.setStatus(t.getStatus());
        taskToBeUpdated.setPriority(t.getPriority());
        taskToBeUpdated.setUser(t.getUser());
        session.update(taskToBeUpdated);
    }

    @Transactional
    @Override
    public void deleteTaskById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Task taskToDelete = session.get(Task.class, id);
        session.delete(taskToDelete);
    }


}
