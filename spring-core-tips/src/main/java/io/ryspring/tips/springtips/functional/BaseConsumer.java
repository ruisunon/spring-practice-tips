package io.ryspring.tips.springtips.functional;

import java.nio.file.Path;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Consumer;

@FunctionalInterface
public interface BaseConsumer extends Consumer<Path> {
  @Override
  default void accept(final Path path) {
    String name = path.getFileName().toString();
    ExecutorService service = Executors.newSingleThreadExecutor(runnable -> {
      Thread thread = new Thread(runnable, "documentId=" + name);
      thread.setDaemon(true);
      return thread;
    });
    Future<?> future = service.submit(() -> {
      baseAccept(path);
      return null;
    });
    try {
      future.get();
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
    } catch (ExecutionException ex) {
      throw new RuntimeException(ex);
    }
  }

  void baseAccept(final Path path) throws Exception;
}